package com.example.javaproject.global.config;

import com.example.javaproject.global.error.ExceptionFilter;
import com.example.javaproject.global.security.jwt.JwtFilter;
import com.example.javaproject.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@RequiredArgsConstructor
@EnableWebFluxSecurity
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .cors().and()
                .csrf().disable()
                .formLogin().disable()
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange()
                    .pathMatchers(HttpMethod.POST, "/user").permitAll()
                    .pathMatchers(HttpMethod.POST, "/user/sign").permitAll()
                    .pathMatchers(HttpMethod.GET, "/user").permitAll()
                    .pathMatchers(HttpMethod.GET, "/user/{id}").authenticated()
                .anyExchange().permitAll()
                .and()
                .addFilterBefore(new ExceptionFilter(objectMapper), SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterBefore(new JwtFilter(jwtTokenProvider), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

}
