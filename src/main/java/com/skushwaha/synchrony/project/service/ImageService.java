package com.skushwaha.synchrony.project.service;

import com.skushwaha.synchrony.project.exception.ImageNotFoundException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.imgur.client.ImgurApiClient;
import com.skushwaha.synchrony.project.imgur.response.ImgurData;
import com.skushwaha.synchrony.project.imgur.response.ImgurResponse;
import com.skushwaha.synchrony.project.model.ImageEntity;
import com.skushwaha.synchrony.project.model.UserEntity;
import com.skushwaha.synchrony.project.repository.ImageRepository;
import com.skushwaha.synchrony.project.request.UserRequest;
import com.skushwaha.synchrony.project.response.ImageResponse;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.response.UserImage;
import java.io.IOException;
import java.util.Collections;
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
      ImgurResponse<ImgurData> imgurResponse = imgurApiClient.uploadImage(image);
      if (imgurResponse != null) {
        ImageEntity savedImage = saveImageData(imgurResponse, username);
        return getApiResponse(toImageResponse(savedImage));
      }
      LOG.error("Image upload failed");
      throw new RuntimeException("Image upload failed");
    }
    LOG.warn("Unable to find registered user to upload image");
    throw new UserNotFoundException("Unable to find registered user to upload image");
  }

  public Response<UserImage> getUserImages(UserRequest request) throws UserNotFoundException {
    UserEntity foundUser = validateAndGetUser(request);
    List<ImageEntity> allImages = imageRepository.findAllByUsername(foundUser.getUsername());
    List<ImageResponse> images = allImages.stream().map(this::toImageResponse).toList();
    UserImage userImage =
        UserImage.builder().user(userService.toUserResponse(foundUser)).images(images).build();
    return getApiResponse(userImage);
  }

  public Response<UserImage> getUserImage(String username, String password, String imageHash)
      throws UserNotFoundException, ImageNotFoundException {
    UserEntity foundUser = validateAndGetUser(username, password);
    ImgurResponse<ImgurData> imgurResponse = imgurApiClient.viewImage(imageHash);

    if (imgurResponse != null) {
      UserImage userImage =
          UserImage.builder()
              .user(userService.toUserResponse(foundUser))
              .images(Collections.singletonList(toImageResponse(imgurResponse)))
              .build();
      return getApiResponse(userImage);
    }

    LOG.warn("Unable to find image for given hash: {}", imageHash);
    throw new ImageNotFoundException("Unable to find image");
  }

  public Response<String> deleteImage(String username, String password, String imageHash)
      throws UserNotFoundException, ImageNotFoundException {
    UserEntity ignored = validateAndGetUser(username, password);
    ImgurResponse<Boolean> imgurResponse = imgurApiClient.deleteImage(imageHash);

    if (imgurResponse != null) {
      return new Response<>(200, true, "Image deleted");
    }

    LOG.warn("Unable to delete image for given hash: {}", imageHash);
    throw new ImageNotFoundException("Unable to delete image");
  }

  private UserEntity validateAndGetUser(UserRequest request) throws UserNotFoundException {
    return validateAndGetUser(request.getUsername(), request.getPassword());
  }

  private UserEntity validateAndGetUser(String username, String password)
      throws UserNotFoundException {
    if (!isValidUser(username, password)) {
      throw new UserNotFoundException("Unable to find user");
    }

    UserEntity foundUser = userService.readUserFromDb(username, password);
    assert foundUser != null;
    return foundUser;
  }

  private ImageEntity saveImageData(ImgurResponse<ImgurData> imgurResponse, String username) {
    ImgurData imgurData = imgurResponse.getData();
    ImageEntity imageEntity =
        new ImageEntity(
            imgurData.getImageHash(), imgurData.getDeleteHash(), imgurData.getImageUrl(), username);
    return imageRepository.save(imageEntity);
  }

  private ImageResponse toImageResponse(ImageEntity imageEntity) {
    return toImageResponse(
        imageEntity.getImageHash(), imageEntity.getDeleteHash(), imageEntity.getImageUrl());
  }

  private ImageResponse toImageResponse(ImgurResponse<ImgurData> imgurResponse) {
    ImgurData imgurData = imgurResponse.getData();
    return toImageResponse(
        imgurData.getImageHash(), imgurData.getDeleteHash(), imgurData.getImageUrl());
  }

  private ImageResponse toImageResponse(String imageHash, String deleteHash, String imageUrl) {
    return ImageResponse.builder()
        .imageHash(imageHash)
        .deleteHash(deleteHash)
        .imageUrl(imageUrl)
        .build();
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
