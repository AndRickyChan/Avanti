package com.ricky.avanti.http.exception;

/**
 * 自定义异常
 * Created by Ricky on 2017-5-2.
 */

public class ApiException extends Exception {

    public ApiException(String message) {
        super(message);
    }
}
