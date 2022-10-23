package com.ynhj.nativemysql.common.filter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

//    private final JwsService jwsService;
//    private final String headerName;

//    @Autowired
//    public JwsFilter(JwsService jwsService, JwsProps jwsProps) {
//        this.jwsService = jwsService;
//        this.headerName = jwsProps.getHeader();
//    }

    @NonNull
    @Override
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
//        String header = exchange.getRequest().getHeaders().getFirst(headerName);
//        var payloadOpt = jwsService.verify(header);
//        if (payloadOpt.isPresent() && payloadOpt.get().available()) {
//            String payload = payloadOpt.get();
//            List<? extends GrantedAuthority> authorities = new ArrayList<>();
//            if (payload.getAuthorities() != null && payload.getAuthorities().size() != 0) {
//                authorities = payload.getAuthorities().stream().map(r -> (GrantedAuthority) r::getAuthority).toList();
//            }
//            var authentication = new UsernamePasswordAuthenticationToken(payload, null, authorities);
//            log.trace("Set security context {}", payload);
//            return chain.filter(exchange)
//                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
//        }
        return chain.filter(exchange);
    }
}
