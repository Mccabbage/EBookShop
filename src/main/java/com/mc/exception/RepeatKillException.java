package com.mc.exception;

/**
 * 重复秒杀异常（运行期异常）
 * created by machao on  2018/4/16 22:31
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
