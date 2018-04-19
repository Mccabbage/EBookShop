package com.mc.exception;

/**
 * 秒杀异常
 * created by machao on  2018/4/16 22:35
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
