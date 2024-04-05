package com.skushwaha.synchrony.project.exception;


public class ImageNotFoundException extends Exception {
  public ImageNotFoundException() {
    super();
  }

  public ImageNotFoundException(String message) {
    super(message);
  }

  public ImageNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
