package com.ynhj.ingress.common.filter;

import com.ynhj.ingress.common.utils.SnowflakeIdUtils;
import com.ynhj.ingress.common.utils.TraceUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Optional;

/**
 * @date: 2022/10/19
 * @author: yangniuhaojiang
 * @title: TraceIdWebFilter
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Configuration
public class TraceIdWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String traceId1 = exchange.getRequest().getHeaders().getFirst(TraceUtils.TRACE_ID);
        final String traceId = Optional.ofNullable(traceId1).orElse(SnowflakeIdUtils.next().toString());
        TraceUtils.addTraceId(traceId);
        return chain.filter(exchange)
                // 放入当前上下文，类似于ThreadLocal
                .contextWrite(context -> {
                    // header 中是否有TRACE-ID
                    Context contextTmp = context.put(TraceUtils.TRACE_ID, traceId);
                    exchange.getAttributes().put(TraceUtils.TRACE_ID, traceId);
                    return contextTmp;
                });
    }
}
