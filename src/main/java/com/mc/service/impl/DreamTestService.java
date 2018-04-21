package com.mc.service.impl;

import com.mc.annotation.AdminOnly;
import org.springframework.stereotype.Service;

/**
 * created by machao on  2018/4/20 0:09
 */
@Service
public class DreamTestService {

    @AdminOnly
    public String aspectTest() throws Exception{
        System.out.println("aspect test");
//        throw new Exception();
        return null;
    }

}
