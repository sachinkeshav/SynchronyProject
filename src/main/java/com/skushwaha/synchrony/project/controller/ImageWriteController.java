package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.ImageNotFoundException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.request.UserImageRequest;
import com.skushwaha.synchrony.project.response.ImageResponse;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.service.ImageService;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/image-writes")
public class ImageWriteController {
  private static final Logger LOG = LoggerFactory.getLogger(ImageWriteController.class);
  private final ImageService imageService;

  @Autowired
  public ImageWriteController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping(path = "/upload-image")
  @PreAuthorize("hasAuthority('SCOPE_write')")
  public Response<ImageResponse> uploadImage(
      final @RequestPart("username") String username,
      final @RequestPart("password") String password,
      final @RequestPart("image") MultipartFile image)
      throws IOException {
    return imageService.uploadImage(username, password, image);
  }

  @DeleteMapping(path = "/delete-image")
  @PreAuthorize("hasAuthority('SCOPE_write')")
  public Response<String> deleteImage(final @RequestBody UserImageRequest request)
      throws UserNotFoundException, ImageNotFoundException {
    LOG.debug("Image delete request: {}", request);
    return imageService.deleteImage(request);
  }
}
