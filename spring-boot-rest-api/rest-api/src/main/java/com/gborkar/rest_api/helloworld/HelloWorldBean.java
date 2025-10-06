package com.gborkar.rest_api.helloworld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class HelloWorldBean {

  @Getter
  @Setter
  String name;

  @Override
  public String toString() {
    return "Hello World, %s" + name;
  }
}
