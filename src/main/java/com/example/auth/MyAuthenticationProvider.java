package com.example.auth;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import java.util.Random;
import java.util.concurrent.ExecutorService;

@Singleton
public class MyAuthenticationProvider implements AuthenticationProvider  {
  private final Scheduler scheduler;
  private final Random rand;
  public MyAuthenticationProvider(@Named(TaskExecutors.IO) ExecutorService executorService) {
    this.scheduler = Schedulers.from(executorService);
    this.rand = new Random();
  }

  @Override
  public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                        AuthenticationRequest<?, ?> authenticationRequest) {
    Flowable<AuthenticationResponse> response = Flowable.create(emitter -> {
      int int_random = rand.nextInt(2);
      if (int_random > 0) {
        emitter.onNext(AuthenticationResponse.success("testUser"));
        emitter.onComplete();
      } else {
        // None of these methods of dealing with an error are available when the RejectionHandler
        // code is executed.
        emitter.onError(AuthenticationResponse.exception("Bad things happened"));
        // emitter.onError(new RuntimeException("My runtime exception"));
        // emitter.onError(AuthenticationResponse.exception("Bad seed"));
      }
    }, BackpressureStrategy.BUFFER);

    response = response.subscribeOn(scheduler);
    return response;
  }
}
