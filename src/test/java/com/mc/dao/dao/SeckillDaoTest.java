package com.mc.dao.dao;

import com.mc.dao.SeckillDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @Author: machao
 * @Date: create in  2018/4/16 15:21
 * @Description: 配置spring和junit整合，junit启动时加载SpringIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    //注入Dao实现类依赖
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void reduceSeckillStock() throws Exception {
        int updateRowCount = seckillDao.reduceSeckillStock(1000,new Date());
        System.out.println(updateRowCount);
    }

}