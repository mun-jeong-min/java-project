package com.example.javaproject.domain.user.handler;

import com.example.javaproject.domain.user.domain.User;
import com.example.javaproject.domain.user.domain.repository.UserRepository;
import com.example.javaproject.domain.user.dto.UserRequest;
import com.example.javaproject.domain.user.exception.UserExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SignupUserHandler {
    private final UserRepository userRepository;

    public void execute(UserRequest request) {

        if(userRepository.findByNickname(request.getNickname()).isPresent()) {
            throw UserExistException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build());

    }
}
