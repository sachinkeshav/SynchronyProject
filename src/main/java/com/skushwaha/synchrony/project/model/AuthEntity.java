package com.skushwaha.synchrony.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
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

  public AuthEntity() {}

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

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "AuthEntity{"
        + "id="
        + id
        + ", clientId='"
        + clientId
        + '\''
        + ", clientSecret='"
        + clientSecret
        + '\''
        + ", accessToken='"
        + accessToken
        + '\''
        + ", refreshToken='"
        + refreshToken
        + '\''
        + ", expiresIn="
        + expiresIn
        + ", timestamp="
        + timestamp
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AuthEntity that = (AuthEntity) o;
    return id == that.id
        && expiresIn == that.expiresIn
        && timestamp == that.timestamp
        && Objects.equals(clientId, that.clientId)
        && Objects.equals(clientSecret, that.clientSecret)
        && Objects.equals(accessToken, that.accessToken)
        && Objects.equals(refreshToken, that.refreshToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, clientId, clientSecret, accessToken, refreshToken, expiresIn, timestamp);
  }
}
