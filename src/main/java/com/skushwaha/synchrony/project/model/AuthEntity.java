package com.skushwaha.synchrony.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "auth")
public class AuthEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "auth_id", nullable = false, updatable = false)
  private long id;

  @Column(name = "client_id", nullable = false)
  private String clientId;

  @Column(name = "client_secret", nullable = false)
  private String clientSecret;

  @Column(name = "access_token", nullable = false)
  private String accessToken;

  @Column(name = "refresh_token", nullable = false)
  private String refreshToken;

  @Column(name = "expires_in", nullable = false)
  private long expiresIn;

  @Column(name = "timestamp", nullable = false)
  private long timestamp;

  public AuthEntity(
      String clientId,
      String clientSecret,
      String accessToken,
      String refreshToken,
      long expiresIn,
      long timestamp) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.expiresIn = expiresIn;
    this.timestamp = timestamp;
  }
}
