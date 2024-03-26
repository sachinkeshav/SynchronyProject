package com.skushwaha.synchrony.project.repository;

import com.skushwaha.synchrony.project.model.ImageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
  List<ImageEntity> findAllByUsername(String username);
}
