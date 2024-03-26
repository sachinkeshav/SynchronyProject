package com.skushwaha.synchrony.project.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserRequest implements Serializable {
  private final @NonNull String username;
  private final @NonNull String password;
}
