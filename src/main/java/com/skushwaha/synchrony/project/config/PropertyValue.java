package com.skushwaha.synchrony.project.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@NoArgsConstructor
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

  @Value("${imgur.sample.username}")
  private String sampleUsername;

  @Value("${imgur.sample.password}")
  private String samplePassword;

  @Value("${imgur.sample.firstname}")
  private String sampleFirstName;

  @Value("${imgur.sample.lastname}")
  private String sampleLastName;

  @Value("${imgur.sample.email}")
  private String sampleEmail;

  @Value("${imgur.sample.phone}")
  private String samplePhone;
}
