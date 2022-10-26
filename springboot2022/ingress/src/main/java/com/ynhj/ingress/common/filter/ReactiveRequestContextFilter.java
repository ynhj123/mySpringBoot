package com.ynhj.ingress.common.filter;

import com.ynhj.ingress.configure.WebLogAspect;
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
        final WebLogAspect.RequestInfo requestInfo = new WebLogAspect.RequestInfo();

        requestInfo.setIp(request.getRemoteAddress().getHostName());
        requestInfo.setUrl(request.getURI().toString());
        requestInfo.setHttpMethod(request.getMethodValue());
        return chain.filter(exchange).contextWrite(ctx -> ctx.put(WebLogAspect.RequestInfo.class, requestInfo));
    }

}
