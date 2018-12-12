package com.test.exception;

/**
 * @className: BusinessException
 * @description:
 * @author: wwb
 * @date: 2018-08-03 15:21:10
 * @version: ver 1.0
 */
public class BusinessException extends Exception {

    private String msg;

    private BusinessException() {

    }

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
