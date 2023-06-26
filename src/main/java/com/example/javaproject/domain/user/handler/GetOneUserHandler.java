package com.example.javaproject.domain.user.handler;

import com.example.javaproject.domain.user.domain.User;
import com.example.javaproject.domain.user.domain.repository.UserRepository;
import com.example.javaproject.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class GetOneUserHandler {
    private final UserRepository userRepository;

    public Mono<UserResponse> execute(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        UserResponse userResponse = UserResponse.builder()
                .name(user.getNickname())
                .password(user.getPassword())
                .build();

        return Mono.just(userResponse);
    }
}
