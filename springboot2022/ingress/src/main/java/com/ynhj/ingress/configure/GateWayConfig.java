package com.ynhj.ingress.configure;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GateWayConfig {
    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb.routes()
                .route(rs -> rs.path("/proxy")
                        .filters(fs -> fs.setPath("/products")
                                .addResponseHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                                .retry(10)).uri("http://localhost:8080/"))
                .route(rs -> rs.path("/login")
                        .filters(fs -> fs.setPath("/auth/login")
                                .addResponseHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                                .retry(10)).uri("http://localhost:8080/"))
                .route(rs -> rs.path("/sign")
                        .filters(fs -> fs.setPath("/auth/sign")
                                .addResponseHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                                .retry(10)).uri("http://localhost:8080/"))
                .build();
    }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
