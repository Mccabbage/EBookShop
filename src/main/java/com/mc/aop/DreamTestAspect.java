package com.mc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * created by machao on  2018/4/20 0:07
 */
@Aspect
@Component
public class DreamTestAspect {

    /**
     * 两种方式都可以用
     */
//    @Pointcut("execution(* com.mc.service.impl.DreamTestService.*(..))")
    @Pointcut("@annotation(com.mc.annotation.AdminOnly)")
    public void pointcutExecution(){

    }
////     通知advice  前置通知
//    @Before("pointcutExecution()")
//    public void before(){
//        System.out.println(" before");
//    }
//
//    // 通知advice 后置通知
//    @After("pointcutExecution()")
//    public void after(){
//        System.out.println(" after");
//    }

    @Around("pointcutExecution()")
    public void around(ProceedingJoinPoint joinPoint){
        System.out.println("around before");
        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            System.out.println("around finally");
        }
    }
//
//    @AfterReturning("pointcutExecution()")
//    public void afterReturning(){
//        System.out.println(" afterReturning");
//    }

//    @AfterThrowing("pointcutExecution()")
//    public void afterThrowing(){
//        System.out.println(" pointcutExecution");
//    }


}
