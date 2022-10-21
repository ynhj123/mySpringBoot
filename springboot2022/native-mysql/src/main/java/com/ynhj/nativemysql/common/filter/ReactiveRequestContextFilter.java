package com.ynhj.nativemysql.common.filter;

import com.ynhj.nativemysql.configure.ReactiveRequestContextHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
public class ReactiveRequestContextFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ReactiveRequestContextHolder.getInstance().init(request);
        return chain.filter(exchange);
    }

}
