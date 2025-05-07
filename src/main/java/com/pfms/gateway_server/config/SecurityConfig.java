package com.pfms.gateway_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/**").permitAll() // Allow unauthenticated access to /auth
                        .anyExchange().authenticated()
                );
        return http.build();
    }
}

