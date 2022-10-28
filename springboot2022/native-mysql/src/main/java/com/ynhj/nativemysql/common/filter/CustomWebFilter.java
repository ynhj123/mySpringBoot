package com.ynhj.nativemysql.common.filter;

import com.ynhj.nativemysql.common.fluxlog.BodyCaptureExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @date: 2022/10/20
 * @author: yangniuhaojiang
 * @title: ReactiveRequestContextFilter
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Configuration
@Slf4j
public class CustomWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        BodyCaptureExchange bodyCaptureExchange = new BodyCaptureExchange(exchange);
        return chain.filter(bodyCaptureExchange).doOnSuccess((se) -> {
            log.info("Body request " + bodyCaptureExchange.getRequest().getFullBody());
            log.info("Body response " + bodyCaptureExchange.getResponse().getFullBody());
        });
    }

}
