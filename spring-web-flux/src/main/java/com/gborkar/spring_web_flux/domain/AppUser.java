package com.gborkar.spring_web_flux.domain;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUser {

  private Long id;
  private String name;
  private String username;
  private String password;

  private Collection<Role> roles;
}
