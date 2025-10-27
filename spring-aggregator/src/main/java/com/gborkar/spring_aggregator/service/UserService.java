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

    public final RestClient restClient;
    
    public List<User> getUsers() {
        return this.restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<List<User>>() {
        });
        
    }
    
}
