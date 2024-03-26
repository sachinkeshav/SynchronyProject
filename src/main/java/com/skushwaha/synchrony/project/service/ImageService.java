package com.skushwaha.synchrony.project.service;

import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.imgur.client.ImgurApiClient;
import com.skushwaha.synchrony.project.imgur.response.ImageData;
import com.skushwaha.synchrony.project.imgur.response.ImageUploadResponse;
import com.skushwaha.synchrony.project.model.ImageEntity;
import com.skushwaha.synchrony.project.model.UserEntity;
import com.skushwaha.synchrony.project.repository.ImageRepository;
import com.skushwaha.synchrony.project.request.UserRequest;
import com.skushwaha.synchrony.project.response.ImageResponse;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.response.UserImage;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
  private static final Logger LOG = LoggerFactory.getLogger(ImageService.class);
  private final ImageRepository imageRepository;
  private final UserService userService;
  private final ImgurApiClient imgurApiClient;

  @Autowired
  public ImageService(
      ImageRepository imageRepository, UserService userService, ImgurApiClient imgurApiClient) {
    this.imageRepository = imageRepository;
    this.userService = userService;
    this.imgurApiClient = imgurApiClient;
  }

  public Response<ImageResponse> uploadImage(String username, String password, MultipartFile image)
      throws IOException {
    if (isValidUser(username, password)) {
      ImageUploadResponse imageUploadResponse = imgurApiClient.uploadImage(image);
      if (imageUploadResponse != null) {
        ImageEntity savedImage = saveImageData(imageUploadResponse, username);
        return getApiResponse(toImageResponse(savedImage));
      }
      LOG.error("Image upload failed");
      throw new RuntimeException("Image upload failed");
    }
    LOG.warn("Unable to find registered user to upload image");
    throw new UserNotFoundException("Unable to find registered user to upload image");
  }

  private ImageEntity saveImageData(ImageUploadResponse imageUploadResponse, String username) {
    ImageData imageData = imageUploadResponse.getData();
    ImageEntity imageEntity =
        new ImageEntity(
            imageData.getImageHash(), imageData.getDeleteHash(), imageData.getImageUrl(), username);
    return imageRepository.save(imageEntity);
  }

  public Response<UserImage> getUserImages(UserRequest request) throws UserNotFoundException {
    UserEntity foundUser = userService.readUserFromDb(request);
    assert foundUser != null;
    List<ImageEntity> allImages = imageRepository.findAllByUsername(foundUser.getUsername());
    List<ImageResponse> images = allImages.stream().map(this::toImageResponse).toList();
    return getApiResponse(new UserImage(userService.toUserResponse(foundUser), images));
  }

  private ImageResponse toImageResponse(ImageEntity imageEntity) {
    return new ImageResponse(
        imageEntity.getImageHash(), imageEntity.getDeleteHash(), imageEntity.getImageUrl());
  }

  private Response<UserImage> getApiResponse(UserImage userImage) {
    return new Response<>(200, true, userImage);
  }

  private Response<ImageResponse> getApiResponse(ImageResponse imageResponse) {
    return new Response<>(200, true, imageResponse);
  }

  private boolean isValidUser(String username, String password) throws UserNotFoundException {
    return userService.readUserFromDb(username, password) != null;
  }
}
