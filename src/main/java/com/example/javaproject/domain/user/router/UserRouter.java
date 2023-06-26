package com.example.javaproject.domain.user.router;

import com.example.javaproject.domain.user.dto.UserRequest;
import com.example.javaproject.domain.user.dto.UserResponse;
import com.example.javaproject.domain.user.handler.GetOneUserHandler;
import com.example.javaproject.domain.user.handler.GetUserListHandler;
import com.example.javaproject.domain.user.handler.PostUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class UserRouter {

    private final GetOneUserHandler getOneUserHandler;
    private final GetUserListHandler getUserListHandler;
    private final PostUserHandler postUserHandler;

    @GetMapping("/user/{id}")
    public Mono<UserResponse> get(@PathVariable("id") Long id) {
        return getOneUserHandler.execute(id);
    }

    @GetMapping(value = "/user", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserResponse> execute() {
        return getUserListHandler.execute();
    }

    @PostMapping("/user")
    public void save(@RequestBody UserRequest request) {
        postUserHandler.execute(request);
    }
}
