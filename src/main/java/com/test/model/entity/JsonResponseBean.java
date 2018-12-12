package com.test.model.entity;

import com.test.model.enums.StatusCode;

/**
 * @className: JsonResponseBean
 * @description:
 * @author: wwb
 * @date: 2018-02-08 10:10:36
 * @version: ver 1.0
 */
public class JsonResponseBean {

    /** 结果码 */
    private String code;
    /** 描述 */
    private String msg;
    /** 结果数据 */
    private Object data;

    private JsonResponseBean() {

    }

    public static JsonResponseBean getInstance(String code) {
        String msg;
        //如果是StatusCode的数据则不需要msg
        if (StatusCode.SUCCESS.getCode().equals(code) || StatusCode.FAIL.getCode().equals(code) || StatusCode.ERROR.getCode().equals(code)) {
            msg = "";
        } else {
            msg = code;
        }
        return getInstance(code, msg);
    }
    public static JsonResponseBean getInstance(String code, String msg) {
        return getInstance(code, null, msg);
    }
    public static JsonResponseBean getInstance(String code, Object data) {
        return getInstance(code, data, null);
    }
    public static JsonResponseBean getInstance(String code, Object data, String msg) {
        JsonResponseBean jsonResponseBean = new JsonResponseBean();
        jsonResponseBean.setCode(StatusCode.getInstance(code).getCode());
        jsonResponseBean.setData(data);
        jsonResponseBean.setMsg(msg);
        return jsonResponseBean;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
