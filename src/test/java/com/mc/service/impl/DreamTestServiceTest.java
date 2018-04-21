package com.mc.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * created by machao on  2018/4/20 15:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class DreamTestServiceTest {
    @Autowired
    private DreamTestService dreamTestService;
    @Test
    public void aspectTest() throws Exception {
        dreamTestService.aspectTest();
    }

}