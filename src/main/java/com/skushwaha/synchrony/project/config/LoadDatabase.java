package com.skushwaha.synchrony.project.config;

import com.skushwaha.synchrony.project.model.AuthEntity;
import com.skushwaha.synchrony.project.model.ImageEntity;
import com.skushwaha.synchrony.project.model.UserEntity;
import com.skushwaha.synchrony.project.model.UserRole;
import com.skushwaha.synchrony.project.repository.AuthRepository;
import com.skushwaha.synchrony.project.repository.ImageRepository;
import com.skushwaha.synchrony.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
  private static final Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(
      PropertyValue propertyValue,
      ImageRepository imageRepository,
      UserRepository userRepository,
      AuthRepository authRepository) {

    return args -> {
      LOG.info("Preloading database");
      authRepository.save(
          new AuthEntity(
              propertyValue.getClientId(),
              propertyValue.getClientSecret(),
              propertyValue.getDefaultAccessToken(),
              propertyValue.getDefaultRefreshToken(),
              propertyValue.getDefaultExpiresIn(),
              System.currentTimeMillis()));
      UserEntity user =
          userRepository.save(
              new UserEntity(
                  propertyValue.getUsername(),
                  propertyValue.getPassword(),
                  propertyValue.getFirstName(),
                  propertyValue.getLastName(),
                  propertyValue.getEmail(),
                  propertyValue.getPhone(),
                  UserRole.ADMIN));
      imageRepository.save(
          new ImageEntity("fsdr", "rwerd", "http://dummy.ur/fsdr.img", user.getUsername()));
      imageRepository.save(
          new ImageEntity("fsdr", "rwerd", "http://dummy.ur/fsdr.img", user.getUsername()));
      imageRepository.save(
          new ImageEntity("fsdr", "rwerd", "http://dummy.ur/fsdr.img", user.getUsername()));
      imageRepository.save(
          new ImageEntity("fsdr", "rwerd", "http://dummy.ur/fsdr.img", user.getUsername()));
    };
  }
}
