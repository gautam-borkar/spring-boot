package com.gborkar.rest_api.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @GetMapping(path = "/hello-world")
  public String getMethodName() {
    return "Hello World!";
  }

  @GetMapping("/hello-world/path-variable/{name}")
  public HelloWorldBean getMethodName(@PathVariable String name) {
    return new HelloWorldBean(String.format("Hello World, %s", name));
  }

}
