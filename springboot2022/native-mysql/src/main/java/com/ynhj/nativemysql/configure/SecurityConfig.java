package com.ynhj.nativemysql.configure;

import com.ynhj.nativemysql.common.filter.JwsFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@EnableWebFluxSecurity
public class SecurityConfig {
    private final JwsFilter jwsFilter;

    @Autowired
    public SecurityConfig(JwsFilter jwsFilter) {
        this.jwsFilter = jwsFilter;
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return authentication -> Mono.empty();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        final var permitAll = new String[]{"/user-svr/country/list"};
        return http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .authorizeExchange().pathMatchers(permitAll).permitAll()
                .pathMatchers("/**").hasAuthority("user")
                .anyExchange().authenticated()
                .and().addFilterAt(jwsFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
}
