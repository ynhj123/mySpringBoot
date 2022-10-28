package com.ynhj.nativemysql.common.fluxlog;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

/**
 * @date: 2022/10/28
 * @author: yangniuhaojiang
 * @title: BodyCaptureExchange
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class BodyCaptureExchange extends ServerWebExchangeDecorator {

    private BodyCaptureRequest bodyCaptureRequest;
    private BodyCaptureResponse bodyCaptureResponse;

    public BodyCaptureExchange(ServerWebExchange exchange) {
        super(exchange);
        this.bodyCaptureRequest = new BodyCaptureRequest(exchange.getRequest());
        this.bodyCaptureResponse = new BodyCaptureResponse(exchange.getResponse());
    }

    @Override
    public BodyCaptureRequest getRequest() {
        return bodyCaptureRequest;
    }

    @Override
    public BodyCaptureResponse getResponse() {
        return bodyCaptureResponse;
    }

}
