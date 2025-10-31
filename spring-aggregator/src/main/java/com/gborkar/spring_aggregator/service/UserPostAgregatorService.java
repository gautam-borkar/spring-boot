package com.gborkar.spring_aggregator.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.gborkar.spring_aggregator.model.Post;
import com.gborkar.spring_aggregator.model.User;
import com.gborkar.spring_aggregator.model.UserPost;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPostAgregatorService {
    
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    
    private final UserService userService;

    private final PostService postService;

    public UserPost getUserAndPostByUserId(long userId) throws InterruptedException, ExecutionException {
        Future<User> userFuture = executorService.submit(() -> userService.getUser(userId));
        Future<List<Post>> postFuture = executorService.submit(() -> postService.getPostsByUserId(userId));

        User user = userFuture.get();
        List<Post> userPosts = postFuture.get();

        return new UserPost(user, userPosts);
    }

}
