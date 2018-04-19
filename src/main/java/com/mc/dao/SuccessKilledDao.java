package com.mc.dao;

import com.mc.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: machao
 * @Date: create in  2018/4/15 0:26
 * @Description:
 */
public interface SuccessKilledDao {

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
