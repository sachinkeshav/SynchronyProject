package com.skushwaha.synchrony.project.imgur.client;

import com.skushwaha.synchrony.project.config.PropertyValue;
import com.skushwaha.synchrony.project.imgur.response.ImgurData;
import com.skushwaha.synchrony.project.imgur.response.ImgurResponse;
import com.skushwaha.synchrony.project.imgur.response.TokenResponse;
import com.skushwaha.synchrony.project.service.AuthService;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
public class ImgurApiClient {
  private static final Logger LOG = LoggerFactory.getLogger(ImgurApiClient.class);
  private final RestTemplate restTemplate;
  private final PropertyValue propertyValue;
  private final AuthService authService;

  @Autowired
  public ImgurApiClient(
      RestTemplateBuilder builder, PropertyValue propertyValue, AuthService authService) {
    this.restTemplate = builder.build();
    this.propertyValue = propertyValue;
    this.authService = authService;
    this.restTemplate.setUriTemplateHandler(
        new DefaultUriBuilderFactory(propertyValue.getBaseUri()));
  }

  public ImgurResponse<ImgurData> uploadImage(MultipartFile image) throws IOException {
    String accessToken = getAccessToken();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    headers.setBearerAuth(accessToken);

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.set("image", Base64.getEncoder().encode(image.getBytes()));
    body.set("type", "base64");
    body.set("title", "Sample upload");
    body.set("description", "This is sample upload");

    HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

    ResponseEntity<ImgurResponse<ImgurData>> uploadResponse =
        restTemplate.exchange(
            propertyValue.getImageUrl(),
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<>() {});
    assert uploadResponse.getStatusCode() == HttpStatus.OK;

    LOG.info("Image upload response: {} ", uploadResponse.getBody());
    return uploadResponse.getBody();
  }

  public ImgurResponse<ImgurData> viewImage(String imageHash) {
    String accessToken = getAccessToken();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    String imageViewUrl = propertyValue.getImageUrl() + "/" + imageHash;
    ResponseEntity<ImgurResponse<ImgurData>> viewResponse =
        restTemplate.exchange(
            imageViewUrl, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});
    assert viewResponse.getStatusCode() == HttpStatus.OK;

    LOG.info("Image view response: {} ", viewResponse.getBody());
    return viewResponse.getBody();
  }

  public ImgurResponse<Boolean> deleteImage(String imageHash) {
    String accessToken = getAccessToken();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    String imageDeleteUrl = propertyValue.getImageUrl() + "/" + imageHash;
    ResponseEntity<ImgurResponse<Boolean>> deleteResponse =
        restTemplate.exchange(
            imageDeleteUrl, HttpMethod.DELETE, entity, new ParameterizedTypeReference<>() {});
    assert deleteResponse.getStatusCode() == HttpStatus.OK;

    LOG.info("Image delete response: {} ", deleteResponse.getBody());
    return deleteResponse.getBody();
  }

  private String getAccessToken() {
    Optional<String> accessToken = authService.getAccessToken();
    if (accessToken.isEmpty()) {
      LOG.warn("Either access_token is null or invalid. Refreshing the token.");
      refreshToken(authService.getRefreshToken());
      accessToken = authService.getAccessToken();
    }

    assert accessToken.isPresent();
    return accessToken.get();
  }

  private void refreshToken(String refreshToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.set("refresh_token", refreshToken);
    body.set("client_id", propertyValue.getClientId());
    body.set("client_secret", propertyValue.getClientSecret());
    body.set("grant_type", "refresh_token");

    HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

    ResponseEntity<TokenResponse> tokenResponse =
        restTemplate.postForEntity(propertyValue.getTokenUrl(), entity, TokenResponse.class);

    assert tokenResponse.getStatusCode() == HttpStatus.OK;
    authService.saveCredential(Objects.requireNonNull(tokenResponse.getBody()));
  }
}
