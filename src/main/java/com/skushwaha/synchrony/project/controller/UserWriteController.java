package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.UserAlreadyExistException;
import com.skushwaha.synchrony.project.request.UserRegisterRequest;
import com.skushwaha.synchrony.project.response.ApiResponse;
import com.skushwaha.synchrony.project.response.UserResponse;
import com.skushwaha.synchrony.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/user-writes")
public class UserWriteController {

  private final UserService userService;

  @Autowired
  public UserWriteController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/register-user")
  @PreAuthorize("hasAuthority('SCOPE_write')")
  public ApiResponse<UserResponse> registerUser(final @RequestBody UserRegisterRequest request)
      throws UserAlreadyExistException {
    log.debug("User registration request: {}", request);
    return userService.registerUser(request);
  }
}
