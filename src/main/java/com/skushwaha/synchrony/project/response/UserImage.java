package com.skushwaha.synchrony.project.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
public class UserImage implements Serializable {
  private final UserResponse user;
  private final List<ImageResponse> images;

  public UserImage(UserResponse user, List<ImageResponse> images) {
    this.user = user;
    this.images = images;
  }

  public UserResponse getUser() {
    return user;
  }

  public List<ImageResponse> getImages() {
    return images;
  }
}
