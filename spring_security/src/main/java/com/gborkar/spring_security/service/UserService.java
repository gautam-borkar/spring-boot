package com.gborkar.spring_security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gborkar.spring_security.domain.AppUser;
import com.gborkar.spring_security.domain.Role;

@Service
public interface UserService {

  void saveUser(AppUser user);

  void saveRole(Role role);

  void addRoleToUser(String user, String roleName);

  List<AppUser> getUsers();

  List<Role> getRoles();
}
