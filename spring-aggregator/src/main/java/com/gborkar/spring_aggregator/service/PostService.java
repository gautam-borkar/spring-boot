package com.gborkar.spring_aggregator.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.gborkar.spring_aggregator.model.Post;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
    private final RestClient restClient;

    private final Executor vThreadExecutor;

    public CompletableFuture<List<Post>> listAllPost() {
        CompletableFuture<List<Post>> cfPost = CompletableFuture.supplyAsync(() -> this.restClient.get().uri("/posts").retrieve().body(new ParameterizedTypeReference<List<Post>>() {
            
        }), vThreadExecutor);
        return cfPost;
    }

    public CompletableFuture<List<Post>> getPostsByUserId(long userId, int page, int size) {
        CompletableFuture<List<Post>> cfPost = CompletableFuture.supplyAsync(() -> this.restClient.get().uri(builder -> builder.path(
                "/users/" + userId + "/posts").queryParam("_page", page).queryParam("_size", size).build(userId))
                .retrieve().body(new ParameterizedTypeReference<List<Post>>() {
                }), vThreadExecutor);
                
        return cfPost;
    }
}
