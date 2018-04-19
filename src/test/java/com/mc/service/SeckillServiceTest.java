package com.mc.service;

import com.mc.dto.Exposer;
import com.mc.dto.SeckillExcution;
import com.mc.entity.Seckill;
import com.mc.exception.RepeatKillException;
import com.mc.exception.SeckillCloseException;
import com.mc.exception.SeckillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * created by machao on  2018/4/17 14:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void queryAllSeckill() throws Exception {
        List<Seckill> list = seckillService.queryAllSeckill();
        logger.info("list = {}", list);
    }

    @Test
    public void queryById() throws Exception {
        long seckillId = 1000L;
        Seckill seckill = seckillService.queryById(seckillId);
        logger.info("seckill = {}",seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long seckillId = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        logger.info("exposer = {}", exposer);
    }

    @Test
    public void excuteSeckill() throws Exception {
        long seckillId = 1000L;
        long userPhone = 1823413248L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            logger.info("exposer = {}", exposer);
            String md5 = exposer.getMd5();
            try {
                SeckillExcution seckillExcution = seckillService.excuteSeckill(seckillId, userPhone, md5);
                logger.info("seckillExcution = {}", seckillExcution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            } catch (SeckillException e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("exposer = {}", exposer);
        }
    }

    @Test
    public void excuteSeckillbyProcedure(){
        long seckillId = 1000L;
        long userPhone = 18234132424L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            logger.info("exposer = {}", exposer);
            String md5 = exposer.getMd5();
            try {
                SeckillExcution seckillExcution = seckillService.excuteSeckillbyProcedure(seckillId, userPhone, md5);
                logger.info("seckillExcution = {}", seckillExcution);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("exposer = {}", exposer);
        }
    }

}