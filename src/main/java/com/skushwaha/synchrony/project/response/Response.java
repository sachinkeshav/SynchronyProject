package com.skushwaha.synchrony.project.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Response<T> implements Serializable {
  private final @NonNull int status;
  private final @NonNull boolean success;
  private final @NonNull T data;
}
