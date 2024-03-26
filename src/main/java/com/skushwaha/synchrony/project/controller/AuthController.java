package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.UserNotAuthorizedException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.imgur.response.TokenResponse;
import com.skushwaha.synchrony.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/oauth")
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(path = "/upload-token")
  @PreAuthorize("hasAuthority('SCOPE_admin')")
  public String uploadToken(
      final @RequestPart("access_token") String accessToken,
      final @RequestPart("refresh_token") String refreshToken,
      final @RequestPart("expires_in") long expiresIn) {
    authService.saveCredential(accessToken, refreshToken, expiresIn);
    return "Access granted: token upload successful";
  }

  @PostMapping(path = "/view-credential")
  @PreAuthorize("hasAuthority('SCOPE_admin')")
  public TokenResponse viewCredential(
      final @RequestPart("username") String username,
      final @RequestPart("password") String password)
      throws UserNotFoundException, UserNotAuthorizedException {
    return authService.viewCredential(username, password);
  }
}
