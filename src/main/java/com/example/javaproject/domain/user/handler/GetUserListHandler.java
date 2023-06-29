package com.example.javaproject.domain.user.handler;

import com.example.javaproject.domain.user.domain.repository.UserRepository;
import com.example.javaproject.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@Component
public class GetUserListHandler {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Flux<UserResponse> execute() {
        return Flux.fromIterable(userRepository.findAll())
                .map(user -> UserResponse.builder()
                        .name(user.getNickname())
                        .password(user.getPassword())
                        .build()
                ).subscribeOn(Schedulers.boundedElastic());
    }
}
