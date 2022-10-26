package com.ynhj.nativemysql.entity;

/**
 * @date: 2022/10/25
 * @author: yangniuhaojiang
 * @title: Role
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */

public enum Role {
    ROLE_USER("user"), ROLE_ADMIN("admin");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getName
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getName() {
        return name;
    }
}
