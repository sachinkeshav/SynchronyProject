package com.skushwaha.synchrony.project.repository;

import com.skushwaha.synchrony.project.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findByEmail(String email);

  UserEntity findByUsername(String username);
}
