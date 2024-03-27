package com.skushwaha.synchrony.project.exception;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.skushwaha.synchrony.project.apierror.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(ImageNotFoundException.class)
  protected ResponseEntity<Object> handleImageNotFound(ImageNotFoundException ex) {
    ApiError apiError = new ApiError(NOT_FOUND);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(UserAlreadyExistException.class)
  protected ResponseEntity<Object> handleUserAlreadyExist(UserAlreadyExistException ex) {
    ApiError apiError = new ApiError(CONFLICT);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(UserNotAuthorizedException.class)
  protected ResponseEntity<Object> handleUserNotAuthorized(UserNotAuthorizedException ex) {
    ApiError apiError = new ApiError(UNAUTHORIZED);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(UserNotFoundException.class)
  protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
    ApiError apiError = new ApiError(NOT_FOUND);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
