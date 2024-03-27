package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.ImageNotFoundException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.request.UserImageRequest;
import com.skushwaha.synchrony.project.request.UserReadRequest;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.response.UserImage;
import com.skushwaha.synchrony.project.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/image-reads")
public class ImageReadController {
  private final ImageService imageService;

  @Autowired
  public ImageReadController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping(path = "/get-all-for-user")
  @PreAuthorize("hasAuthority('SCOPE_read')")
  public Response<UserImage> getAllImageForUser(final @RequestBody UserReadRequest request)
      throws UserNotFoundException {
    log.debug("User request for all image: {}", request);
    return imageService.getUserImages(request);
  }

  @PostMapping(path = "/get-image")
  @PreAuthorize("hasAuthority('SCOPE_read')")
  public Response<UserImage> getImage(final @RequestBody UserImageRequest request)
      throws UserNotFoundException, ImageNotFoundException {
    log.debug("Single image read request: {}", request);
    return imageService.getUserImage(request);
  }
}
