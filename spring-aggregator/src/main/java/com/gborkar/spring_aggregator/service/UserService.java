package com.gborkar.spring_aggregator.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.gborkar.spring_aggregator.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final RestClient restClient;

    private final Executor vThreadExecutor;
    
    public CompletableFuture<List<User>> listAllUsers() {
        CompletableFuture<List<User>> cfUsers = CompletableFuture.supplyAsync(() ->
            this.restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<List<User>>() {
            }), vThreadExecutor
        );

        return cfUsers;
    }
    
    public CompletableFuture<User> getUser(long userId) {
        CompletableFuture<User> cfUser = CompletableFuture.supplyAsync(() -> 
            this.restClient.get().uri("/users/" + userId).retrieve().body(User.class), vThreadExecutor
            );
        
        return cfUser;
    }
    
}
