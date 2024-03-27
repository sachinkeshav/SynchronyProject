package com.skushwaha.synchrony.project.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class TokenRequest implements Serializable {
  private final @NonNull String accessToken;
  private final @NonNull String refreshToken;
  private final long expiresIn;
}
