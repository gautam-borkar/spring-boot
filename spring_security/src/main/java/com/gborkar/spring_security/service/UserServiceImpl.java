package com.gborkar.spring_security.service;

import java.util.List;

import com.gborkar.spring_security.domain.AppUser;
import com.gborkar.spring_security.domain.Role;
import com.gborkar.spring_security.repository.RoleRepository;
import com.gborkar.spring_security.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private UserRepository userRepo;

  private RoleRepository roleRepo;

  @Override
  public void saveUser(AppUser user) {
    log.info("Saving user {} to the database", user.getUsername());
    userRepo.save(user);
  }

  @Override
  public void saveRole(Role role) {
    log.info("Saving role {} to the database", role.getName());
    roleRepo.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    log.info("Adding role {} to the user {}", roleName, username);
    AppUser user = userRepo.findByUsername(username);
    Role role = roleRepo.findByName(roleName);
    user.getRoles().add(role);
  }

  @Override
  public List<AppUser> getUsers() {
    log.info("Get all users from database");
    return userRepo.findAll();
  }

  @Override
  public List<Role> getRoles() {
    return roleRepo.findAll();
  }

}
