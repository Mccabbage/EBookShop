package com.mc.aop;

import org.springframework.stereotype.Component;

/**
 * created by machao on  2018/4/20 19:36
 */
@Component
public class ReallySubject implements Subject{

    public void play(){
        System.out.println("play");
    }

    public void hello(){
        System.out.println("hello");
    }

}
