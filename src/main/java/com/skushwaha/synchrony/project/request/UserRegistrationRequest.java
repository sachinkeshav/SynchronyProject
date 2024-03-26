package com.skushwaha.synchrony.project.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class UserRegistrationRequest implements Serializable {
  @NotBlank(message = "Username cannot be blank")
  private String username;

  @NotBlank(message = "Password can not be blank")
  private String password;

  @NotBlank(message = "First name can not be blank")
  private String firstName;

  @NotBlank(message = "Last name can not be blank")
  private String lastName;

  @NotBlank(message = "Email can not be blank")
  @Email(message = "Please provide a valid email id")
  private String email;

  @NotBlank(message = "Phone can not be blank")
  private String phone;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "UserRegistrationRequest{"
        + "username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", phone='"
        + phone
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserRegistrationRequest that = (UserRegistrationRequest) o;
    return Objects.equals(username, that.username)
        && Objects.equals(password, that.password)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(email, that.email)
        && Objects.equals(phone, that.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, firstName, lastName, email, phone);
  }
}
