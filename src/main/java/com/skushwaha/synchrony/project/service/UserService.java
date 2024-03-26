package com.skushwaha.synchrony.project.service;

import com.skushwaha.synchrony.project.exception.UserAlreadyExistException;
import com.skushwaha.synchrony.project.exception.UserNotFoundException;
import com.skushwaha.synchrony.project.model.UserEntity;
import com.skushwaha.synchrony.project.repository.UserRepository;
import com.skushwaha.synchrony.project.request.UserRegistrationRequest;
import com.skushwaha.synchrony.project.request.UserRequest;
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

  public Response<UserResponse> registerUser(UserRegistrationRequest request)
      throws UserAlreadyExistException {
    if (checkIfUserExist(request.getEmail())) {
      LOG.error("User already exists for this email. Registration request: {}", request);
      throw new UserAlreadyExistException("User already exists for this email");
    }

    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(request, userEntity);
    userEntity.setPassword(encodePassword(request.getPassword()));
    UserEntity savedUser = userRepository.save(userEntity);
    LOG.info("saved user: {}", savedUser);
    return getApiResponse(toUserResponse(savedUser));
  }

  public Response<UserResponse> readUser(UserRequest request) throws UserNotFoundException {
    return getApiResponse(getUserResponse(request));
  }

  public UserResponse getUserResponse(UserRequest request) throws UserNotFoundException {
    return getUserResponse(request.getUsername(), request.getPassword());
  }

  public UserResponse getUserResponse(String username, String password)
      throws UserNotFoundException {
    return toUserResponse(readUserFromDb(username, password));
  }

  public UserEntity readUserFromDb(UserRequest request) throws UserNotFoundException {
    return readUserFromDb(request.getUsername(), request.getPassword());
  }

  public UserEntity readUserFromDb(String username, String password) throws UserNotFoundException {
    UserEntity userEntity = userRepository.findByUsername(username);
    if (userEntity == null) {
      LOG.error("Unable to find user with given username {}", username);
      throw new UserNotFoundException("Unable to find user");
    }

    if (passwordEncoder.matches(password, userEntity.getPassword())) {
      return userEntity;
    }

    LOG.error(
        "Unable to find user with given username and password combination. Username: {}, Password: {}",
        username,
        password);
    throw new UserNotFoundException("Unable to find user");
  }

  public UserResponse toUserResponse(UserEntity userEntity) {
    return new UserResponse(
        userEntity.getUsername(),
        userEntity.getFirstName(),
        userEntity.getLastName(),
        userEntity.getEmail(),
        userEntity.getPhone());
  }

  private Response<UserResponse> getApiResponse(UserResponse userResponse) {
    return new Response<>(200, true, userResponse);
  }

  private boolean checkIfUserExist(String email) {
    return userRepository.findByEmail(email) != null;
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
