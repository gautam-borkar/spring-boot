package com.gborkar.spring_web_flux.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gborkar.spring_web_flux.domain.AppUser;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




@RestController
@RequestMapping(path = "/users")
public class UserController {

    @PostMapping()
    public Mono<ResponseEntity<AppUser>> createUser(@RequestBody Mono<AppUser> user) {
        AppUser appUser = new AppUser();
        return Mono.just(ResponseEntity.created(null).body(appUser));
    }
    
    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<AppUser>> getUserById(@PathVariable UUID id) {
        AppUser user = new AppUser();
        return Mono.just(ResponseEntity.ok().body(user));
    }
    
    @GetMapping
    public Flux<ResponseEntity<AppUser>> getUsers(@RequestParam(defaultValue = "10") int start, @RequestParam(defaultValue = "0") int offset) {
        AppUser user = new AppUser();
        return Flux.just(ResponseEntity.ok().body(user));
    }
    
}
