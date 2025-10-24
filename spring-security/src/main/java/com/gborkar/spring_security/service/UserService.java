package com.gborkar.spring_security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gborkar.spring_security.domain.AppUser;
import com.gborkar.spring_security.domain.Role;
import com.gborkar.spring_security.repository.RoleRepository;
import com.gborkar.spring_security.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepo;

  private final RoleRepository roleRepo;
  
  private final BCryptPasswordEncoder passwordEncoder;

  public Long saveUser(AppUser user) {
    log.info("Saving user {} to the database", user.getUsername());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepo.save(user).getId();
  }

  
  public Long saveRole(Role role) {
    log.info("Saving role {} to the database", role.getName());
    return roleRepo.save(role).getId();
  }

  
  public void addRoleToUser(String username, String roleName) {
    log.info("Adding role {} to the user {}", roleName, username);
    AppUser user = userRepo.findByUsername(username);
    Role role = roleRepo.findByName(roleName);
    user.getRoles().add(role);
    userRepo.save(user);
  }

  
  public List<AppUser> getUsers() {
    log.info("Get all users from database");
    return userRepo.findAll();
  }

  
  public List<Role> getRoles() {
    return roleRepo.findAll();
  }

  
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser user = userRepo.findByUsername(username);

    if (user == null) {
        log.error("User not found");
        throw new UsernameNotFoundException("User not found");
    } 

    Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
    user.getRoles().forEach(role -> authority.add(new SimpleGrantedAuthority(role.getName())));
    return new User(user.getUsername(), user.getPassword(), authority);
  }
}
