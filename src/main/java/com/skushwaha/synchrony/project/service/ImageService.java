package com.skushwaha.synchrony.project.service;

import com.skushwaha.synchrony.project.exception.ImageNotFoundException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.imgur.client.ImgurApiClient;
import com.skushwaha.synchrony.project.imgur.response.ImgurData;
import com.skushwaha.synchrony.project.imgur.response.ImgurResponse;
import com.skushwaha.synchrony.project.model.ImageEntity;
import com.skushwaha.synchrony.project.model.UserEntity;
import com.skushwaha.synchrony.project.repository.ImageRepository;
import com.skushwaha.synchrony.project.request.UserImageRequest;
import com.skushwaha.synchrony.project.request.UserReadRequest;
import com.skushwaha.synchrony.project.response.ApiResponse;
import com.skushwaha.synchrony.project.response.ImageResponse;
import com.skushwaha.synchrony.project.response.UserImage;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageService {
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

  public ApiResponse<ImageResponse> uploadImage(
      String username, String password, MultipartFile image, String title, String description)
      throws IOException, UserNotFoundException {
    if (isValidUser(username, password)) {
      ImgurResponse<ImgurData> imgurResponse =
          imgurApiClient.uploadImage(image, title, description);
      if (imgurResponse != null) {
        ImageEntity savedImage = saveImageData(imgurResponse, username);
        return getApiResponse(toImageResponse(savedImage));
      }
      log.error("Image upload failed");
      throw new RuntimeException("Image upload failed");
    }
    log.warn("Unable to find registered user to upload image");
    throw new UserNotFoundException("Unable to find registered user to upload image");
  }

  public ApiResponse<UserImage> getUserImages(UserReadRequest request) throws UserNotFoundException {
    UserEntity foundUser = validateAndGetUser(request);
    List<ImageEntity> allImages = imageRepository.findAllByUsername(foundUser.getUsername());
    List<ImageResponse> images = allImages.stream().map(this::toImageResponse).toList();
    UserImage userImage =
        UserImage.builder().user(userService.toUserResponse(foundUser)).images(images).build();
    return getApiResponse(userImage);
  }

  public ApiResponse<UserImage> getUserImage(UserImageRequest request)
      throws UserNotFoundException, ImageNotFoundException {
    UserEntity foundUser = validateAndGetUser(request.getUsername(), request.getPassword());
    ImgurResponse<ImgurData> imgurResponse = imgurApiClient.viewImage(request.getImageHash());

    if (imgurResponse != null) {
      UserImage userImage =
          UserImage.builder()
              .user(userService.toUserResponse(foundUser))
              .images(Collections.singletonList(toImageResponse(imgurResponse)))
              .build();
      return getApiResponse(userImage);
    }

    log.warn("Unable to find image for given hash: {}", request.getImageHash());
    throw new ImageNotFoundException("Unable to find image");
  }

  public ApiResponse<String> deleteImage(UserImageRequest request)
      throws UserNotFoundException, ImageNotFoundException {
    UserEntity ignored = validateAndGetUser(request.getUsername(), request.getPassword());
    ImgurResponse<Boolean> imgurResponse = imgurApiClient.deleteImage(request.getImageHash());

    if (imgurResponse != null) {
      imageRepository.deleteByImageHashAndUsername(request.getImageHash(), request.getUsername());
      return new ApiResponse<>(200, true, "Image deleted");
    }

    log.warn("Unable to delete image for given hash: {}", request.getImageHash());
    throw new ImageNotFoundException("Unable to delete image");
  }

  private UserEntity validateAndGetUser(UserReadRequest request) throws UserNotFoundException {
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
            imgurData.getImageHash(),
            imgurData.getDeleteHash(),
            imgurData.getImageUrl(),
            imgurData.getTitle(),
            imgurData.getDescription(),
            username);
    return imageRepository.save(imageEntity);
  }

  private ImageResponse toImageResponse(ImageEntity imageEntity) {
    return toImageResponse(
        imageEntity.getImageHash(),
        imageEntity.getDeleteHash(),
        imageEntity.getImageUrl(),
        imageEntity.getTitle(),
        imageEntity.getDescription());
  }

  private ImageResponse toImageResponse(ImgurResponse<ImgurData> imgurResponse) {
    ImgurData imgurData = imgurResponse.getData();
    return toImageResponse(
        imgurData.getImageHash(),
        imgurData.getDeleteHash(),
        imgurData.getImageUrl(),
        imgurData.getTitle(),
        imgurData.getDescription());
  }

  private ImageResponse toImageResponse(
      String imageHash, String deleteHash, String imageUrl, String title, String description) {
    return ImageResponse.builder()
        .imageHash(imageHash)
        .deleteHash(deleteHash)
        .imageUrl(imageUrl)
        .title(title)
        .description(description)
        .build();
  }

  private ApiResponse<UserImage> getApiResponse(UserImage userImage) {
    return new ApiResponse<>(200, true, userImage);
  }

  private ApiResponse<ImageResponse> getApiResponse(ImageResponse imageResponse) {
    return new ApiResponse<>(200, true, imageResponse);
  }

  private boolean isValidUser(String username, String password) throws UserNotFoundException {
    return userService.readUserFromDb(username, password) != null;
  }
}
