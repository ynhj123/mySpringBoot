package com.test.model.enums;

/**
 * @className: StatusCode
 * @description:
 * @author: wwb
 * @date: 2018-08-03 10:12:09
 * @version: ver 1.0
 */
public enum StatusCode {

    /** 成功 */
    SUCCESS("1"),
    /** 失败 */
    FAIL("0"),
    /** 错误 */
    ERROR("-1"),
    /** 未登录 */
    NOT_LOGIN("530");

    private String code;
    private String msg;

    StatusCode(String code) {
        this.code = code;
    }

    public static StatusCode getInstance(String code) {
        switch (code) {
            case "1":
                return SUCCESS;
            case "0":
                return FAIL;
            case "-1":
                return ERROR;
            case "530":
                return NOT_LOGIN;
            default:
                return FAIL;
        }
    }

    public String getCode() {
        return code;
    }

}
