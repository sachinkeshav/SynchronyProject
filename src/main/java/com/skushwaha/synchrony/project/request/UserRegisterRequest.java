package com.skushwaha.synchrony.project.request;

import jakarta.validation.constraints.Email;
import java.io.Serializable;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserRegisterRequest implements Serializable {
  private final @NonNull String username;

  private final @NonNull String password;

  private final @NonNull String firstName;

  private final @NonNull String lastName;

  @Email(message = "Please provide a valid email id")
  private final @NonNull String email;

  private final @NonNull String phone;
}
