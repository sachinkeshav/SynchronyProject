package com.skushwaha.synchrony.project.exception;

public class UserNotAuthorizedException extends Exception {
  public UserNotAuthorizedException() {
    super();
  }

  public UserNotAuthorizedException(String message) {
    super(message);
  }

  public UserNotAuthorizedException(String message, Throwable cause) {
    super(message, cause);
  }
}
