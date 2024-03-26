package com.skushwaha.synchrony.project.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

@JsonInclude(Include.NON_EMPTY)
public class ImageResponse implements Serializable {
  private final String imageHash;
  private final String deleteHash;
  private final String imageUrl;

  public ImageResponse(String imageHash, String deleteHash, String imageUrl) {
    this.imageHash = imageHash;
    this.deleteHash = deleteHash;
    this.imageUrl = imageUrl;
  }

  public String getImageHash() {
    return imageHash;
  }

  public String getDeleteHash() {
    return deleteHash;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}
