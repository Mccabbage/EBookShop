package com.mc.service;

import com.mc.dto.Exposer;
import com.mc.dto.SeckillExcution;
import com.mc.entity.Seckill;
import com.mc.exception.RepeatKillException;
import com.mc.exception.SeckillCloseException;
import com.mc.exception.SeckillException;

import java.util.List;

/**
 * @Author: machao
 * @Date: create in  2018/4/16 22:11
 * @Description:
 */
public interface SeckillService {

    /**
     * 获取所有的秒杀
     * @return
     */
    public List<Seckill> queryAllSeckill();

    /**
     * 根据Id获取秒杀
     * @param seckillId
     * @return
     */
    public Seckill queryById(long seckillId);

    /**
     * 秒杀开始输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5)
                throws SeckillException,RepeatKillException,SeckillCloseException;

    /**
     * 执行秒杀操作by存储过程
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExcution excuteSeckillbyProcedure(long seckillId, long userPhone, String md5);

}
