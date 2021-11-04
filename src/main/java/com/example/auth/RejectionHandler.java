package com.example.auth;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.authentication.DefaultAuthorizationExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Replaces(DefaultAuthorizationExceptionHandler.class)
public class RejectionHandler extends DefaultAuthorizationExceptionHandler {

  @Override
  public MutableHttpResponse<?> handle(HttpRequest request, AuthorizationException exception) {
    return super.handle(request, exception);
  }
}
