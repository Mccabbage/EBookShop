package com.mc.aop;

import java.lang.reflect.Proxy;

/**
 * created by machao on  2018/4/20 19:39
 */
public class Test {
    public static void main(String[] args) {
//        Subject jdkProxySubject = (Subject) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{Subject.class}, new JdkProxySubject(new ReallySubject()));
//        jdkProxySubject.hello();
//        jdkProxySubject.play();
    }
}
