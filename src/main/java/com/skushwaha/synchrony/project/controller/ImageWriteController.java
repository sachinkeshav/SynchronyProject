package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.exception.ImageNotFoundException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.request.UserImageRequest;
import com.skushwaha.synchrony.project.response.ApiResponse;
import com.skushwaha.synchrony.project.response.ImageResponse;
import com.skushwaha.synchrony.project.service.ImageService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/v1/image-writes")
public class ImageWriteController {
  private final ImageService imageService;

  @Autowired
  public ImageWriteController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping(path = "/upload-image")
  @PreAuthorize("hasAuthority('SCOPE_write')")
  public ApiResponse<ImageResponse> uploadImage(
      final @RequestPart("username") String username,
      final @RequestPart("password") String password,
      final @RequestPart("image") MultipartFile image,
      final @RequestPart("title") String title,
      final @RequestPart("description") String description)
      throws IOException, UserNotFoundException {
    return imageService.uploadImage(username, password, image, title, description);
  }

  @DeleteMapping(path = "/delete-image")
  @PreAuthorize("hasAuthority('SCOPE_write')")
  public ApiResponse<String> deleteImage(final @RequestBody UserImageRequest request)
      throws UserNotFoundException, ImageNotFoundException {
    log.debug("Image delete request: {}", request);
    return imageService.deleteImage(request);
  }
}
