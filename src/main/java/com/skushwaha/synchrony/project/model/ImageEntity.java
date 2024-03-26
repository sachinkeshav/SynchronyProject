package com.skushwaha.synchrony.project.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
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

  @Column(name = "username", nullable = false)
  private String username;

  public ImageEntity() {}

  public ImageEntity(String imageHash, String deleteHash, String imageUrl, String username) {
    this.imageHash = imageHash;
    this.deleteHash = deleteHash;
    this.imageUrl = imageUrl;
    this.username = username;
  }

  public String getImageHash() {
    return imageHash;
  }

  public void setImageHash(String imageHash) {
    this.imageHash = imageHash;
  }

  public String getDeleteHash() {
    return deleteHash;
  }

  public void setDeleteHash(String deleteHash) {
    this.deleteHash = deleteHash;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "ImageEntity{"
        + "id="
        + id
        + ", imageHash='"
        + imageHash
        + '\''
        + ", deleteHash='"
        + deleteHash
        + '\''
        + ", imageUrl='"
        + imageUrl
        + '\''
        + ", username='"
        + username
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ImageEntity that = (ImageEntity) o;
    return id == that.id
        && Objects.equals(imageHash, that.imageHash)
        && Objects.equals(deleteHash, that.deleteHash)
        && Objects.equals(imageUrl, that.imageUrl)
        && Objects.equals(username, that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, imageHash, deleteHash, imageUrl, username);
  }
}
