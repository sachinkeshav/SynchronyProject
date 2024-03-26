package com.skushwaha.synchrony.project.imgur.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(Include.NON_EMPTY)
public class TokenResponse implements Serializable {
  @SerializedName("access_token")
  private String accessToken;

  @SerializedName("expires_in")
  private long expiresIn;

  @SerializedName("token_type")
  private String tokenType;

  @SerializedName("scope")
  private String scope;

  @SerializedName("refresh_token")
  private String refreshToken;

  @SerializedName("account_id")
  private long accountId;

  @SerializedName("account_username")
  private String accountUsername;

  public TokenResponse() {}

  public TokenResponse(
      String accessToken,
      long expiresIn,
      String tokenType,
      String scope,
      String refreshToken,
      long accountId,
      String accountUsername) {
    this.accessToken = accessToken;
    this.expiresIn = expiresIn;
    this.tokenType = tokenType;
    this.scope = scope;
    this.refreshToken = refreshToken;
    this.accountId = accountId;
    this.accountUsername = accountUsername;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }

  public String getAccountUsername() {
    return accountUsername;
  }

  public void setAccountUsername(String accountUsername) {
    this.accountUsername = accountUsername;
  }

  @Override
  public String toString() {
    return "TokenResponse{"
        + "accessToken='"
        + accessToken
        + '\''
        + ", expiresIn="
        + expiresIn
        + ", tokenType='"
        + tokenType
        + '\''
        + ", scope='"
        + scope
        + '\''
        + ", refreshToken='"
        + refreshToken
        + '\''
        + ", accountId="
        + accountId
        + ", accountUsername='"
        + accountUsername
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TokenResponse that = (TokenResponse) o;
    return expiresIn == that.expiresIn
        && accountId == that.accountId
        && Objects.equals(accessToken, that.accessToken)
        && Objects.equals(tokenType, that.tokenType)
        && Objects.equals(scope, that.scope)
        && Objects.equals(refreshToken, that.refreshToken)
        && Objects.equals(accountUsername, that.accountUsername);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        accessToken, expiresIn, tokenType, scope, refreshToken, accountId, accountUsername);
  }
}
