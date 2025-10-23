package com.gborkar.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gborkar.spring_security.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String roleName);

}
