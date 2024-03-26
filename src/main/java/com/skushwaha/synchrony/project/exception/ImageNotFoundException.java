package com.skushwaha.synchrony.project.exception;

import org.apache.coyote.BadRequestException;

public class ImageNotFoundException extends BadRequestException {
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
