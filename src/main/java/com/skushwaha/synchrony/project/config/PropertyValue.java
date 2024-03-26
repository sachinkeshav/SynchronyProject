package com.skushwaha.synchrony.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:properties.yaml", factory = YamlPropertySourceFactory.class)
public class PropertyValue {
  @Value("${imgur.client-id}")
  private String clientId;

  @Value("${imgur.client-secret}")
  private String clientSecret;

  @Value("${imgur.base-uri}")
  private String baseUri;

  @Value("${imgur.token-url}")
  private String tokenUrl;

  @Value("${imgur.image-url}")
  private String imageUrl;

  @Value("${imgur.default.access-token}")
  private String defaultAccessToken;

  @Value("${imgur.default.refresh-token}")
  private String defaultRefreshToken;

  @Value("${imgur.default.expires-in}")
  private long defaultExpiresIn;

  @Value("${imgur.default.username}")
  private String username;

  @Value("${imgur.default.password}")
  private String password;

  @Value("${imgur.default.firstname}")
  private String firstName;

  @Value("${imgur.default.lastname}")
  private String lastName;

  @Value("${imgur.default.email}")
  private String email;

  @Value("${imgur.default.phone}")
  private String phone;

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getBaseUri() {
    return baseUri;
  }

  public String getTokenUrl() {
    return tokenUrl;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getDefaultAccessToken() {
    return defaultAccessToken;
  }

  public String getDefaultRefreshToken() {
    return defaultRefreshToken;
  }

  public long getDefaultExpiresIn() {
    return defaultExpiresIn;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
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
