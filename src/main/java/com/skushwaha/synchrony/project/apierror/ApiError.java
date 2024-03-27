package com.skushwaha.synchrony.project.apierror;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.skushwaha.synchrony.project.config.LowerCaseClassNameResolver;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonTypeInfo(
    include = JsonTypeInfo.As.WRAPPER_OBJECT,
    use = JsonTypeInfo.Id.CUSTOM,
    property = "error",
    visible = true)
@JsonInclude(Include.NON_EMPTY)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiError {

  private HttpStatus status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;

  private String message;

  private ApiError() {
    timestamp = LocalDateTime.now();
  }

  public ApiError(HttpStatus status) {
    this();
    this.status = status;
  }
}
