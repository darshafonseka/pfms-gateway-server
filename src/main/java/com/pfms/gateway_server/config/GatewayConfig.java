package com.pfms.gateway_server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/users/**")
                        .uri("http://localhost:8081"))
                .route("finance-service", r -> r.path("/finance/**")
                        .uri("http://localhost:8082"))
                .route("report-service", r -> r.path("/report/**")
                        .uri("http://localhost:8083"))
                .build();
    }
}
