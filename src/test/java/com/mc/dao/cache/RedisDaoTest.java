package com.mc.dao.cache;

import com.mc.dao.SeckillDao;
import com.mc.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * created by machao on  2018/4/18 14:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    private long seckillId = 1000L;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void getSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill == null){
            seckill = seckillDao.queryById(seckillId);
            if(seckill != null){
                String result = redisDao.setSeckill(seckill);
                System.out.println(result);
                Seckill seckillAgain = redisDao.getSeckill(seckillId);
                System.out.println(seckillAgain);
            }
        }
    }
}