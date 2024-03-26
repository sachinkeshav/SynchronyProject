package com.skushwaha.synchrony.project.imgur.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

@JsonInclude(Include.NON_EMPTY)
public class ImageUploadResponse implements Serializable {
  private int status;
  private boolean success;
  private ImageData data;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public ImageData getData() {
    return data;
  }

  public void setData(ImageData data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "ImageUploadResponse{"
        + "status="
        + status
        + ", success="
        + success
        + ", data="
        + data
        + '}';
  }
}
