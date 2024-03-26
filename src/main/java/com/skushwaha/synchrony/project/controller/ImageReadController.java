package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.request.UserRequest;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.response.UserImage;
import com.skushwaha.synchrony.project.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/image-reads")
public class ImageReadController {
  private static final Logger LOG = LoggerFactory.getLogger(ImageReadController.class);
  private final ImageService imageService;

  @Autowired
  public ImageReadController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping(path = "/get-all-for-user")
  @PreAuthorize("hasAuthority('SCOPE_read')")
  public Response<UserImage> getAllImageForUser(final @RequestBody UserRequest request)
      throws UserNotFoundException {
    LOG.debug("User request for image: {}", request);
    return imageService.getUserImages(request);
  }

  @PostMapping(path = "/get-image")
  @PreAuthorize("hasAuthority('SCOPE_read')")
  public String getImage(
      final @RequestPart("username") String username,
      final @RequestPart("password") String password,
      final @RequestPart("imageHash") String imageHash) {
    return "Access granted: user updated successfully";
  }
}
