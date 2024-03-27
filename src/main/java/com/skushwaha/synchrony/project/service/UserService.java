package com.skushwaha.synchrony.project.service;

import com.skushwaha.synchrony.project.exception.UserAlreadyExistException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.model.UserEntity;
import com.skushwaha.synchrony.project.repository.UserRepository;
import com.skushwaha.synchrony.project.request.UserReadRequest;
import com.skushwaha.synchrony.project.request.UserRegisterRequest;
import com.skushwaha.synchrony.project.response.Response;
import com.skushwaha.synchrony.project.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Autowired
  public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public Response<UserResponse> registerUser(UserRegisterRequest request)
      throws UserAlreadyExistException {
    validateNewUser(request);

    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(request, userEntity);
    userEntity.setPassword(encodePassword(request.getPassword()));

    LOG.debug("Saving the user: {}", userEntity);

    UserEntity savedUser = userRepository.save(userEntity);
    return getApiResponse(toUserResponse(savedUser));
  }

  public Response<UserResponse> readUser(UserReadRequest request) throws UserNotFoundException {
    return getApiResponse(getUserResponse(request));
  }

  public UserResponse getUserResponse(UserReadRequest request) throws UserNotFoundException {
    return getUserResponse(request.getUsername(), request.getPassword());
  }

  public UserResponse getUserResponse(String username, String password)
      throws UserNotFoundException {
    return toUserResponse(readUserFromDb(username, password));
  }

  public UserEntity readUserFromDb(UserReadRequest request) throws UserNotFoundException {
    return readUserFromDb(request.getUsername(), request.getPassword());
  }

  public UserEntity readUserFromDb(String username, String password) throws UserNotFoundException {
    UserEntity userEntity = userRepository.findByUsername(username);
    if (userEntity == null) {
      LOG.warn("Unable to find user with given username {}", username);
      throw new UserNotFoundException("Unable to find user");
    }

    if (passwordEncoder.matches(password, userEntity.getPassword())) {
      return userEntity;
    }

    LOG.warn(
        "Unable to find user with given username and password combination. Username: {}, Password: {}",
        username,
        password);
    throw new UserNotFoundException("Unable to find user");
  }

  public UserResponse toUserResponse(UserEntity userEntity) {
    return UserResponse.builder()
        .username(userEntity.getUsername())
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .email(userEntity.getEmail())
        .phone(userEntity.getPhone())
        .build();
  }

  private void validateNewUser(UserRegisterRequest request) throws UserAlreadyExistException {
    if (checkIfUserExistForUsername(request.getUsername())) {
      LOG.warn("User already exists for this username. Registration request: {}", request);
      throw new UserAlreadyExistException("User already exists for this username");
    }

    if (checkIfUserExistForEmail(request.getEmail())) {
      LOG.warn("User already exists for this email. Registration request: {}", request);
      throw new UserAlreadyExistException("User already exists for this email");
    }

    if (checkIfUserExistForPhone(request.getPhone())) {
      LOG.warn("User already exists for this phone. Registration request: {}", request);
      throw new UserAlreadyExistException("User already exists for this phone");
    }
  }

  private Response<UserResponse> getApiResponse(UserResponse userResponse) {
    return new Response<>(200, true, userResponse);
  }

  private boolean checkIfUserExistForUsername(String username) {
    return userRepository.findByUsername(username) != null;
  }

  private boolean checkIfUserExistForEmail(String email) {
    return userRepository.findByEmail(email) != null;
  }

  private boolean checkIfUserExistForPhone(String phone) {
    return userRepository.findByPhone(phone) != null;
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
