package com.skushwaha.synchrony.project.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

@JsonInclude(Include.NON_EMPTY)
public class Response<T> implements Serializable {
  private final int status;
  private final boolean success;
  private final T data;

  public Response(int status, boolean success, T data) {
    this.status = status;
    this.success = success;
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public boolean isSuccess() {
    return success;
  }

  public T getData() {
    return data;
  }
}
