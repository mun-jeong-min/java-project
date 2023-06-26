package com.example.javaproject.domain.user.router;

import com.example.javaproject.domain.user.domain.User;
import com.example.javaproject.domain.user.domain.repository.UserRepository;
import com.example.javaproject.domain.user.dto.UserRequest;
import com.example.javaproject.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@RestController
public class UserRouter {

    private final UserRepository userRepository;

    @GetMapping(value = "/user", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserResponse> execute() {
        return Flux.fromIterable(userRepository.findAll())
                .map(user -> UserResponse.builder()
                        .name(user.getNickname())
                        .password(user.getPassword())
                        .build()
                ).subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping("/user")
    public void save(@RequestBody UserRequest request) {
        userRepository.save(User.builder()
                        .nickname(request.getNickname())
                        .password(request.getPassword())
                .build());
    }
}
