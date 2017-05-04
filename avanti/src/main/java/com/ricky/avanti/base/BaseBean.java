package com.ricky.avanti.base;

/**
 * 结果返回基类
 * Created by Ricky on 2017-5-2.
 */

public class BaseBean<T> {
    private int code ;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
