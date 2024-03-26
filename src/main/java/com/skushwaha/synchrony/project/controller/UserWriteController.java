package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.UserAlreadyExistException;
import com.skushwaha.synchrony.project.request.UserRegistrationRequest;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.response.UserResponse;
import com.skushwaha.synchrony.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-writes")
public class UserWriteController {
  private static final Logger LOG = LoggerFactory.getLogger(UserWriteController.class);

  private final UserService userService;

  @Autowired
  public UserWriteController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/register-user", consumes = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('SCOPE_write')")
  public Response<UserResponse> registerUser(final @RequestBody UserRegistrationRequest request)
      throws UserAlreadyExistException {
    LOG.debug("User registration request: {}", request.toString());
    return userService.registerUser(request);
  }
}
