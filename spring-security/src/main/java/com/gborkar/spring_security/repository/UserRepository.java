package com.gborkar.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gborkar.spring_security.domain.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

  AppUser findByUsername(String username);

}
