package com.example.javaproject.domain.user.handler;

import com.example.javaproject.domain.user.domain.User;
import com.example.javaproject.domain.user.domain.repository.UserRepository;
import com.example.javaproject.domain.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostUserHandler {
    private final UserRepository userRepository;

    public void execute(UserRequest request) {
        userRepository.save(User.builder()
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build());
    }
}
