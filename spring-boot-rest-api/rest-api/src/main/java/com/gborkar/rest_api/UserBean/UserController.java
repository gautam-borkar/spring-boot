package com.gborkar.rest_api.UserBean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(path = "/users")
  public List<UserBean> listUsers() {
    return userService.listUsers();
  }

  @GetMapping(path = "/users/{id}")
  public UserBean getUserById(@PathVariable int id) {
    return userService.getUserById(id);
  }

  @PostMapping(path = "/users")
  public ResponseEntity<Object> createUser(@RequestBody UserBean user) {
    userService.save(user);

    return ResponseEntity.created(null).build();
  }
}
