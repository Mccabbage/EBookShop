package com.mc.dto;

/**
 * 所有的ajax返回类型， 封装json结果
 * created by machao on  2018/4/17 20:54
 */
public class DreamResult<T> {
    private boolean success;

    private T data;

    private String message;

    public DreamResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public DreamResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
