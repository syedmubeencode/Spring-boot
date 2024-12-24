package com.example.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    public Mono<ServerResponse> createUser(ServerRequest request) {
        return ServerResponse.ok().bodyValue("User created successfully");
    }
}

