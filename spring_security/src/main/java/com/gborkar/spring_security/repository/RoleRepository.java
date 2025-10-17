package com.gborkar.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gborkar.spring_security.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String roleName);

}
