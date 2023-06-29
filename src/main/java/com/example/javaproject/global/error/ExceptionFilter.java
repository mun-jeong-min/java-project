package com.example.javaproject.global.error;

import com.example.javaproject.global.error.exception.CustomException;
import com.example.javaproject.global.error.exception.ErrorCode;
import com.example.javaproject.global.error.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class ExceptionFilter implements WebFilter {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .onErrorResume(CustomException.class, e -> {
                    ErrorCode errorCode = e.getErrorCode();
                    ErrorResponse errorResponse = new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
                    String errorResult;
                    try {
                        errorResult = objectMapper.writeValueAsString(errorResponse);
                    } catch (JsonProcessingException ex) {
                        throw new RuntimeException(ex);
                    }
                    exchange.getResponse().setStatusCode(HttpStatus.valueOf(errorCode.getStatus()));
                    exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(errorResult.getBytes())));
                });
    }
}
