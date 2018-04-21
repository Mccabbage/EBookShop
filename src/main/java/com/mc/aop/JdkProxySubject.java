package com.mc.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * created by machao on  2018/4/20 19:35
 */
@Component
public class JdkProxySubject implements InvocationHandler {
    private ReallySubject reallySubject;

    public JdkProxySubject(ReallySubject reallySubject) {
        this.reallySubject = reallySubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        try {
            method.invoke(reallySubject,args);
        } catch (Exception e) {
            System.out.println("exception");
        } finally {
            System.out.println("after");
        }
        return null;
    }
}
