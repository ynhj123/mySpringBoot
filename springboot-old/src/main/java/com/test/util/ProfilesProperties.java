package com.test.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: ProfilesProperties
 * @description:
 * @author: wwb
 * @date: 2018-08-29 11:20:54
 * @version: ver 1.0
 */
@Component("profilesProperties")
@ConfigurationProperties(prefix = "spring.profiles")
public class ProfilesProperties {

    private String active;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
