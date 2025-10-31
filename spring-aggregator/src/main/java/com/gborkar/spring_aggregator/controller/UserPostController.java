package com.gborkar.spring_aggregator.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gborkar.spring_aggregator.model.UserPost;
import com.gborkar.spring_aggregator.service.UserPostAgregatorService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class UserPostController {

    private final UserPostAgregatorService userPostAgregatorService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Welcome!");
    }

    @GetMapping("/userpost/{userId}")
    public ResponseEntity<UserPost> retrieveUserPost(@PathVariable long userId,
            @RequestParam(defaultValue = "10") int page, @RequestParam(defaultValue = "10") int size)
            throws InterruptedException, ExecutionException {

        UserPost userPost = userPostAgregatorService.retrieveUserAndPostByUserIdByVirtualThread(userId, page, size);
        return ResponseEntity.ok(userPost);
    }
}
