package com.skushwaha.synchrony.project.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "syf_user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", nullable = false, updatable = false)
  private long id;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "phone", nullable = false, unique = true)
  private String phone;

  @Column(name = "role", nullable = false)
  private Role role = Role.USER;

  public UserEntity() {}

  public UserEntity(
      String username,
      String password,
      String firstName,
      String lastName,
      String email,
      String phone,
      Role role) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
  }

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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "UserEntity{"
        + "id="
        + id
        + ", username='"
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
        + ", role="
        + role
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserEntity that = (UserEntity) o;
    return id == that.id
        && Objects.equals(username, that.username)
        && Objects.equals(password, that.password)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(email, that.email)
        && Objects.equals(phone, that.phone)
        && role == that.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, firstName, lastName, email, phone, role);
  }
}
