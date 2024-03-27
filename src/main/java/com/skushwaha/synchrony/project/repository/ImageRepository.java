package com.skushwaha.synchrony.project.repository;

import com.skushwaha.synchrony.project.model.ImageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
  List<ImageEntity> findAllByUsername(String username);

  @Transactional
  void deleteByImageHashAndUsername(String imageHash, String username);
}
