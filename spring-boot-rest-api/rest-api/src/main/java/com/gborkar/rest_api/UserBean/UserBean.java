package com.gborkar.rest_api.UserBean;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UserBean {

  @Getter
  @Setter
  long id;

  @Getter
  @Setter
  String name;

  @Getter
  @Setter
  LocalDate dateOfBirth;
}
