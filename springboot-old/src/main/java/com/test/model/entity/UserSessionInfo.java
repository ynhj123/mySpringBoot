package com.test.model.entity;

/**
 * 用户session
 *
 * @author xiaohelin
 * @create 2017-12-06 11:27
 **/
public class UserSessionInfo {

    private String uid;
    private String realName;
    private boolean manager;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
