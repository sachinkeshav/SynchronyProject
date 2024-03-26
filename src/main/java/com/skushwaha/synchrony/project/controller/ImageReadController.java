package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.request.UserRequest;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.response.UserImage;
import com.skushwaha.synchrony.project.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/image-reads")
public class ImageReadController {
  private final ImageService imageService;

  @Autowired
  public ImageReadController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping(path = "/get-all")
  @PreAuthorize("hasAuthority('SCOPE_read')")
  public Response<UserImage> getAllUserImage(final @Valid @RequestBody UserRequest request)
      throws UserNotFoundException {
    return imageService.getUserImages(request);
  }
}
