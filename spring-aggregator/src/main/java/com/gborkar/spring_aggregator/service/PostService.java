package com.gborkar.spring_aggregator.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.gborkar.spring_aggregator.model.Post;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
    private final RestClient restClient;

    public List<Post> listAllPost() {
        return this.restClient.get().uri("/posts").retrieve().body(new ParameterizedTypeReference<List<Post>>() {
        });
    }

    public List<Post> getPostsByUserId(long userId) {
        return this.restClient.get().uri("/users/" + userId + "/posts").retrieve().body(new ParameterizedTypeReference<List<Post>>(){});
    }
}
