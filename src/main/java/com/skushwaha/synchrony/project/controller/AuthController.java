package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.UserNotAuthorizedException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.imgur.response.TokenResponse;
import com.skushwaha.synchrony.project.request.TokenRequest;
import com.skushwaha.synchrony.project.request.UserReadRequest;
import com.skushwaha.synchrony.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(path = "/upload-token")
  @PreAuthorize("hasAuthority('SCOPE_admin')")
  public String uploadToken(final @RequestBody TokenRequest request) {
    authService.saveCredential(request);
    return "Access granted: token upload successful";
  }

  @PostMapping(path = "/view-credential")
  @PreAuthorize("hasAuthority('SCOPE_admin')")
  public TokenResponse viewCredential(final @RequestBody UserReadRequest request)
      throws UserNotFoundException, UserNotAuthorizedException {
    return authService.viewCredential(request);
  }
}
