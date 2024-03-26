package com.skushwaha.synchrony.project.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

@JsonInclude(Include.NON_EMPTY)
public class UserResponse implements Serializable {
  private final String username;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String phone;

  public UserResponse(
      String username, String firstName, String lastName, String email, String phone) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
  }

  public String getUsername() {
    return username;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }
}
