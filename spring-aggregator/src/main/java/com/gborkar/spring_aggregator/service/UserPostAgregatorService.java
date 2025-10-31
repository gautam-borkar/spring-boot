package com.gborkar.spring_aggregator.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.gborkar.spring_aggregator.model.Post;
import com.gborkar.spring_aggregator.model.User;
import com.gborkar.spring_aggregator.model.UserPost;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPostAgregatorService {
    
    private final UserService userService;

    private final PostService postService;

    public UserPost retrieveUserAndPostByUserIdByCompletableFuture(long userId)
            throws InterruptedException, ExecutionException {
        CompletableFuture<User> cfUser = userService.getUser(userId);
        CompletableFuture<List<Post>> cfUserPost = postService.getPostsByUserId(userId);

        return CompletableFuture.allOf(cfUser, cfUserPost).whenComplete((success, e) -> {
            if (e != null) {
                e.printStackTrace();
            }
        })
                .thenApply((v) -> new UserPost(cfUser.join(), cfUserPost.join())).join();
    }

    public UserPost retrieveUserAndPostByUserIdByVirtualThread(long userId, int page, int size)
            throws InterruptedException, ExecutionException {
        CompletableFuture<User> cfUser = userService.getUser(userId);
        CompletableFuture<List<Post>> cfPosts = postService.getPostsByUserId(userId, page, size);

        return CompletableFuture.allOf(cfUser, cfPosts).thenApply((v) -> new UserPost(cfUser.join(), cfPosts.join()))
                .join();
    }
}
