package com.skushwaha.synchrony.project.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ApiResponse<T> implements Serializable {
  private final int status;
  private final boolean success;
  private final T data;
}
