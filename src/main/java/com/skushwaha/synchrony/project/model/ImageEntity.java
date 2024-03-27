package com.skushwaha.synchrony.project.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "image")
public class ImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "image_id", nullable = false, updatable = false)
  private long id;

  @Column(name = "image_hash", nullable = false)
  private String imageHash;

  @Column(name = "delete_hash", nullable = false)
  private String deleteHash;

  @Column(name = "image_url", nullable = false)
  private String imageUrl;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "username", nullable = false)
  private String username;

  public ImageEntity(
      String imageHash,
      String deleteHash,
      String imageUrl,
      String title,
      String description,
      String username) {
    this.imageHash = imageHash;
    this.deleteHash = deleteHash;
    this.imageUrl = imageUrl;
    this.title = title;
    this.description = description;
    this.username = username;
  }
}
