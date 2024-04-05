package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.request.UserReadRequest;
import com.skushwaha.synchrony.project.response.ApiResponse;
import com.skushwaha.synchrony.project.response.UserResponse;
import com.skushwaha.synchrony.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/user-reads")
public class UserReadController {
  private final UserService userService;

  @Autowired
  public UserReadController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/get-user")
  @PreAuthorize("hasAuthority('SCOPE_read')")
  public ApiResponse<UserResponse> getUser(final @RequestBody UserReadRequest request)
      throws UserNotFoundException {
    log.debug("User read request: {}", request);
    return userService.readUser(request);
  }
}
