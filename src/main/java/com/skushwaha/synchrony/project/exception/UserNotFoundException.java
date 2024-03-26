package com.skushwaha.synchrony.project.exception;

import org.apache.coyote.BadRequestException;

public class UserNotFoundException extends BadRequestException {
  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
