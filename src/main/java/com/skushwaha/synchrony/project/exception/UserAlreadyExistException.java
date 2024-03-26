package com.skushwaha.synchrony.project.exception;

import org.apache.coyote.BadRequestException;

public class UserAlreadyExistException extends BadRequestException {
  public UserAlreadyExistException() {
    super();
  }

  public UserAlreadyExistException(String message) {
    super(message);
  }

  public UserAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
