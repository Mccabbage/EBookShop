package com.mc.exception;

/**
 * 秒杀关闭异常
 * created by machao on  2018/4/16 22:34
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
