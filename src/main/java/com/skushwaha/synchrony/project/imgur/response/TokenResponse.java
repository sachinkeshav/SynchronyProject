package com.skushwaha.synchrony.project.imgur.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class TokenResponse implements Serializable {
  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expires_in")
  private long expiresIn;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("scope")
  private String scope;

  @JsonProperty("refresh_token")
  private String refreshToken;

  @JsonProperty("account_id")
  private long accountId;

  @JsonProperty("account_username")
  private String accountUsername;
}
