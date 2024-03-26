package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.request.UserRequest;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.response.UserResponse;
import com.skushwaha.synchrony.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-reads")
public class UserReadController {
  private final UserService userService;

  @Autowired
  public UserReadController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/read")
  @PreAuthorize("hasAuthority('SCOPE_read')")
  public Response<UserResponse> getUser(final @Valid @RequestBody UserRequest readRequest)
      throws UserNotFoundException {
    return userService.readUser(readRequest);
  }

  @GetMapping(path = "read-test")
  @PreAuthorize("hasAuthority('SCOPE_read')")
  public String readTest() {
    return "Access granted: read test successful";
  }
}
