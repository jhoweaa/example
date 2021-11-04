package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller
@Secured(SecurityRule.IS_AUTHENTICATED)
public class MyController {
  @Get("test")
  public String getTestResponse() {
    return "Test succeeded.";
  }
}
