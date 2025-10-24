package com.gborkar.spring_security.domain;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String username;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<Role> roles;
}
