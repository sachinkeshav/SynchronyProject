package com.skushwaha.synchrony.project.repository;

import com.skushwaha.synchrony.project.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findByUsername(String username);

  UserEntity findByEmail(String email);

  UserEntity findByPhone(String phone);
}
