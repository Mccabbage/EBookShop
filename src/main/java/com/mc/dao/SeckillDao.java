package com.mc.dao;

import com.mc.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: machao
 * @Date: create in  2018/4/15 0:26
 * @Description:
 */
public interface SeckillDao {

    /**
     * 查询所有的秒杀
     * @return
     */
    List<Seckill> queryAllSeckill();

    /**
     * 查一个秒杀
     * @param seckillId
     * @return
     */
    Seckill queryById(@Param("seckillId") long seckillId);

    /**
     * 减库存
     * @param seckillId
     * @param seckillTime
     * @return
     */
     int reduceSeckillStock(@Param("seckillId") long seckillId, @Param("seckillTime") Date seckillTime);

     void killByProceduer(Map<String, Object> map);
}
