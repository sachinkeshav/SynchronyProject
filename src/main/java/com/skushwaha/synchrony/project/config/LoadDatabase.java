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
      UserEntity root =
          userRepository.save(
              new UserEntity(
                  propertyValue.getUsername(),
                  propertyValue.getPassword(),
                  propertyValue.getFirstName(),
                  propertyValue.getLastName(),
                  propertyValue.getEmail(),
                  propertyValue.getPhone(),
                  UserRole.ADMIN));
      UserEntity user =
          userRepository.save(
              new UserEntity(
                  propertyValue.getSampleUsername(),
                  propertyValue.getSamplePassword(),
                  propertyValue.getSampleFirstName(),
                  propertyValue.getSampleLastName(),
                  propertyValue.getSampleEmail(),
                  propertyValue.getSamplePhone(),
                  UserRole.USER));
      imageRepository.save(
          new ImageEntity(
              "PRUIA7z",
              "aMimEJomzFkRTzg",
              "https://i.imgur.com/PRUIA7z.jpeg",
              "Sample upload",
              "This is sample upload",
              root.getUsername()));
      imageRepository.save(
          new ImageEntity(
              "fT0wf4m",
              "qqOQ7X4VTCJDjqj",
              "https://i.imgur.com/fT0wf4m.jpeg",
              "Sample upload",
              "This is sample upload",
              root.getUsername()));
      imageRepository.save(
          new ImageEntity(
              "IgQ8F7V",
              "InRc5BwnincI05F",
              "https://i.imgur.com/IgQ8F7V.jpeg",
              "Sample upload",
              "This is sample upload",
              user.getUsername()));
      imageRepository.save(
          new ImageEntity(
              "QCryb56",
              "Pc6xU5qklCYGI71",
              "https://i.imgur.com/QCryb56.jpeg",
              "Sample upload",
              "This is sample upload",
              user.getUsername()));
      imageRepository.save(
          new ImageEntity(
              "57t1CyH",
              "wdtHDVYM3Cbxvpj",
              "https://i.imgur.com/57t1CyH.jpeg",
              "Sample upload",
              "This is sample upload",
              user.getUsername()));
    };
  }
}
