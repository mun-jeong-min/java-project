package com.example.javaproject.domain.user.handler;

import com.example.javaproject.domain.user.domain.User;
import com.example.javaproject.domain.user.domain.repository.UserRepository;
import com.example.javaproject.domain.user.dto.TokenResponse;
import com.example.javaproject.domain.user.dto.UserRequest;
import com.example.javaproject.domain.user.exception.UserNotFoundException;
import com.example.javaproject.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class SignInUserHandler {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public Mono<TokenResponse> execute(UserRequest request) {

        User user = userRepository.findByNickname(request.getNickname())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(!user.getPassword().equals(request.getPassword())) {
            throw UserNotFoundException.EXCEPTION;
        }

        return Mono.just(TokenResponse.builder()
                        .accessToken(jwtTokenProvider.generateAccessToken(user.getNickname()))
                .build());
    }
}
