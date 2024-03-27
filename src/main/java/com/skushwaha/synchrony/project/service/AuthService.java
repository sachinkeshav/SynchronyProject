package com.skushwaha.synchrony.project.service;

import com.skushwaha.synchrony.project.config.PropertyValue;
import com.skushwaha.synchrony.project.exception.UserNotAuthorizedException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.imgur.response.TokenResponse;
import com.skushwaha.synchrony.project.model.AuthEntity;
import com.skushwaha.synchrony.project.model.UserEntity;
import com.skushwaha.synchrony.project.model.UserRole;
import com.skushwaha.synchrony.project.repository.AuthRepository;
import com.skushwaha.synchrony.project.request.TokenRequest;
import com.skushwaha.synchrony.project.request.UserReadRequest;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {
  private final AuthRepository authRepository;
  private final PropertyValue propertyValue;
  private final UserService userService;

  @Autowired
  public AuthService(
      AuthRepository authRepository, PropertyValue propertyValue, UserService userService) {
    this.authRepository = authRepository;
    this.propertyValue = propertyValue;
    this.userService = userService;
  }

  public TokenResponse viewCredential(UserReadRequest request)
      throws UserNotFoundException, UserNotAuthorizedException {
    UserEntity user = userService.readUserFromDb(request);

    if (user.getUserRole() == UserRole.ADMIN) {
      AuthEntity authEntity = readCredentialFromDb();
      TokenResponse tokenResponse = new TokenResponse();
      BeanUtils.copyProperties(authEntity, tokenResponse);
      return tokenResponse;
    }

    log.warn("User not authorized to view credential");
    throw new UserNotAuthorizedException("User not authorized to view credential");
  }

  public Optional<String> getAccessToken() {
    AuthEntity credential = readCredentialFromDb();

    if (credential != null && isValid(credential)) {
      return Optional.of(credential.getAccessToken());
    }

    return Optional.empty();
  }

  public String getRefreshToken() {
    AuthEntity credential = readCredentialFromDb();
    return credential.getRefreshToken();
  }

  public void saveCredential(TokenRequest request) {
    saveCredential(request.getAccessToken(), request.getRefreshToken(), request.getExpiresIn());
  }

  public void saveCredential(TokenResponse tokenResponse) {
    saveCredential(
        tokenResponse.getAccessToken(),
        tokenResponse.getRefreshToken(),
        tokenResponse.getExpiresIn());
  }

  public void saveCredential(String accessToken, String refreshToken, long expiresIn) {
    AuthEntity authEntity = readCredentialFromDb();

    authEntity.setAccessToken(accessToken);
    authEntity.setRefreshToken(refreshToken);
    authEntity.setExpiresIn(expiresIn);
    authEntity.setTimestamp(System.currentTimeMillis());

    authRepository.save(authEntity);
  }

  private AuthEntity readCredentialFromDb() {
    return authRepository.findByClientIdAndClientSecret(
        propertyValue.getClientId(), propertyValue.getClientSecret());
  }

  private boolean isValid(AuthEntity credential) {
    long expiresIn = credential.getExpiresIn();
    long timestamp = credential.getTimestamp();
    long currentTimeMillis = System.currentTimeMillis();
    return currentTimeMillis < timestamp + expiresIn;
  }
}
