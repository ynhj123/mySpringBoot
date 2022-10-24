package com.ynhj.nativemysql.common.filter;

import com.alibaba.fastjson2.JSON;
import com.ynhj.nativemysql.common.utils.JwtUtil;
import com.ynhj.nativemysql.entiry.JwtUser;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwsFilter implements WebFilter {
    @NonNull
    @Override
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        String header = exchange.getRequest().getHeaders().getFirst(JwtUtil.TOKEN_HEADER);
        JwtUser payloadOpt = (JwtUser) JwtUtil.checkJWT(header.replace(JwtUtil.TOKEN_PREFIX, ""));
        if (payloadOpt != null) {
            String payload = JSON.toJSONString(payloadOpt);
            List<? extends GrantedAuthority> authorities = new ArrayList<>();
            if (payloadOpt.getAuthorities() != null && payloadOpt.getAuthorities().size() != 0) {
                authorities = payloadOpt.getAuthorities().stream().map(r -> (GrantedAuthority) r::getAuthority).toList();
            }
            var authentication = new UsernamePasswordAuthenticationToken(payload, null, authorities);
            log.trace("Set security context {}", payload);
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        return chain.filter(exchange);
    }
}
