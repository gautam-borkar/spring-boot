package com.gborkar.spring_aggregator.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.gborkar.spring_aggregator.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final RestClient restClient;
    
    public List<User> listAllUsers() {
        return this.restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<List<User>>() {
        });

    }
    
    public User getUser(long userId) {
        return this.restClient.get().uri("/users/" + userId).retrieve().body(User.class);
    }
    
}
