package com.ynhj.nativemysql.configure;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @date: 2022/10/20
 * @author: yangniuhaojiang
 * @title: ReactiveRequestContextHolder
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class ReactiveRequestContextHolder {
    private static final ReactiveRequestContextHolder reactiveRequestContextHolder = new ReactiveRequestContextHolder();

    public static ReactiveRequestContextHolder getInstance() {
        return reactiveRequestContextHolder;
    }

    private final WebLogAspect.RequestInfo requestInfo = new WebLogAspect.RequestInfo();

    public void init(ServerHttpRequest request) {
        requestInfo.setIp(request.getRemoteAddress().getHostName());
        requestInfo.setUrl(request.getURI().toString());
        requestInfo.setHttpMethod(request.getMethodValue());
    }


    public WebLogAspect.RequestInfo get() {
        return requestInfo;
    }


}
