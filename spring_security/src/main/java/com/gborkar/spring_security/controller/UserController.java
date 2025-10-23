package com.gborkar.spring_security.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gborkar.spring_security.domain.AppUser;
import com.gborkar.spring_security.domain.Role;
import com.gborkar.spring_security.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class UserController {
  private final UserService userService;

  @GetMapping(path = "/users")
  public ResponseEntity<List<AppUser>> getUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @PostMapping(path = "/users")
  public ResponseEntity<Long> saveUser(@RequestBody AppUser user) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(user));
  }

  @PostMapping(path = "/roles")
  public ResponseEntity<Long> saveRoles(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/roles").toUriString());
    return ResponseEntity.created(uri).body(userService.saveRole(role));
  }

  @PostMapping(path = "/users/{username}/roles/{rolename}")
  public ResponseEntity<?> addRoleToUser(@PathVariable String username, @PathVariable String roleName) {
    userService.addRoleToUser(username, roleName);
    return ResponseEntity.ok().build();
  }
}
