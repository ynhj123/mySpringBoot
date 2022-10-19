package com.ynhj.nativemysql.common.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @date: 2022/10/19
 * @author: yangniuhaojiang
 * @title: GlobalException
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class GlobalException extends ResponseStatusException {
    public GlobalException(HttpStatus status) {
        super(status);
    }

    public GlobalException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public GlobalException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public GlobalException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}
