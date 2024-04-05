package com.skushwaha.synchrony.project.repository;

import com.skushwaha.synchrony.project.model.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
  AuthEntity findByClientIdAndClientSecret(String clientId, String clientSecret);
}
