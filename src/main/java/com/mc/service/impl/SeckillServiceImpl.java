package com.mc.service.impl;

import com.mc.dao.SeckillDao;
import com.mc.dao.SuccessKilledDao;
import com.mc.dao.cache.RedisDao;
import com.mc.dto.Exposer;
import com.mc.dto.SeckillExcution;
import com.mc.entity.Seckill;
import com.mc.entity.SuccessKilled;
import com.mc.enums.SeckillStateEnum;
import com.mc.exception.RepeatKillException;
import com.mc.exception.SeckillCloseException;
import com.mc.exception.SeckillException;
import com.mc.service.SeckillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by machao on  2018/4/16 22:39
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    //md5盐值字符串，用于混淆MD5
    private final String salt = "sdjfgse732q*(8990-q3w302";

    public List<Seckill> queryAllSeckill() {
        return seckillDao.queryAllSeckill();
    }

    public Seckill queryById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
//        优化点：缓存优化，超时的基础上维护一致性
        //1.访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill == null){
            //2.访问数据库
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null){
                return new Exposer(false, seckillId);
            }else {
                //放入redis
                redisDao.setSeckill(seckill);
            }
        }
        Date start = seckill.getStartTime();
        Date end = seckill.getEndTime();
        Date now = new Date();
        if(now.getTime() < start.getTime() ||
                now.getTime() > end.getTime())
            return new Exposer(false, seckillId, now.getTime(), start.getTime(), end.getTime());
        String md5 = getMd5(seckillId);//转换特定字符串的过程，不可逆
        return new Exposer(true, md5, seckillId);
    }

    private String getMd5(long seckillId){
        String base = seckillId + "/" +salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    /**
     * 保证事务方法的执行时间尽可能的短
     */
    @Transactional
    public SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMd5(seckillId)))
            throw new SeckillException("seckill data rewrite");
        try {
            //减库存+记录购买行为
            int updateCount = seckillDao.reduceSeckillStock(seckillId, new Date());
            if(updateCount <= 0){
                //未更新到记录
                throw new SeckillCloseException("seckill end");
            } else {
                //记录购买行为 todo
                int insertCount = 1;
                if(insertCount == 0){
                    //重复秒杀
                    throw new RepeatKillException("seckill repeat");
                }else{
                    return new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS);
                }
            }
        } catch (SeckillCloseException e1){
            throw  e1;
        } catch (RepeatKillException e2){
            throw  e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //将所有编译期异常转换成运行时异常   Spring的声明式异常只对运行期异常做回滚
            throw  new SeckillException("seckill inner  error:" + e.getMessage());
        }
    }

    @Transactional
    public SeckillExcution excuteSeckillbyProcedure(long seckillId, long userPhone, String md5) {
        if (md5 == null || !md5.equals(getMd5(seckillId)))
            return new SeckillExcution(seckillId, SeckillStateEnum.DATA_REWRITE);
        Map<String, Object> map = new HashMap<>();
        Date killTime = new Date();
        map.put("seckillId",seckillId);
        map.put("userPhone",userPhone);
        map.put("killTime",killTime);
        map.put("result",null);
        try {
            seckillDao.killByProceduer(map);
            int result = MapUtils.getInteger(map, "result", -2);
            if(result == 1){
                SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
            }else {
                return new SeckillExcution(seckillId, SeckillStateEnum.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new SeckillExcution(seckillId, SeckillStateEnum.INNER_ERROR);
        }
    }
}
