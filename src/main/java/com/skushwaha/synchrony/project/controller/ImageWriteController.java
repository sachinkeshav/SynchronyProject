package com.skushwaha.synchrony.project.controller;

import com.skushwaha.synchrony.project.response.ImageResponse;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.service.ImageService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/image-writes")
public class ImageWriteController {

  private final ImageService imageService;

  @Autowired
  public ImageWriteController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @PreAuthorize("hasAuthority('SCOPE_write')")
  public Response<ImageResponse> uploadImage(
      final @RequestPart("username") String username,
      final @RequestPart("password") String password,
      final @RequestPart("image") MultipartFile image)
      throws IOException {
    return imageService.uploadImage(username, password, image);
  }
}