package com.gborkar.spring_security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gborkar.spring_security.domain.AppUser;
import com.gborkar.spring_security.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class UserController {
  private UserService userService;

  @GetMapping(path = "/users")
  public ResponseEntity<List<AppUser>> getUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }
}
