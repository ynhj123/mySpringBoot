package com.ynhj.nativemysql.common.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * @date: 2022/10/19
 * @author: yangniuhaojiang
 * @title: GlobalErrorAttributes
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Configuration
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions errorAttributeOptions) {
        Map<String, Object> map = super.getErrorAttributes(request, errorAttributeOptions);

        if (getError(request) instanceof GlobalException) {
            GlobalException ex = (GlobalException) getError(request);
            map.put("exception", ex.getClass().getSimpleName());
            map.put("message", ex.getMessage());
            map.put("status", ex.getStatus().value());
            map.put("error", ex.getStatus().getReasonPhrase());
            return map;
        }

        map.put("exception", "SystemException");
        map.put("message", "System Error , Check logs!");
        map.put("status", "500");
        map.put("error", " System Error ");
        return map;
    }
}
