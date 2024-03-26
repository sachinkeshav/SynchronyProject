package com.skushwaha.synchrony.project.imgur.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
public class ImageData implements Serializable {
  @SerializedName("id")
  private String imageHash;

  @SerializedName("deletehash")
  private String deleteHash;

  @SerializedName("account_id")
  private String accountId;

  @SerializedName("account_url")
  private String accountUrl;

  @SerializedName("ad_type")
  private String adType;

  @SerializedName("ad_url")
  private String adUrl;

  private String title;
  private String description;
  private String name;
  private String type;
  private int width;
  private int height;
  private long size;
  private long views;
  private String section;
  private int vote;
  private int bandwidth;
  private boolean animated;
  private boolean favorite;

  @SerializedName("in_gallery")
  private boolean inGallery;

  @SerializedName("in_most_viral")
  private boolean inMostViral;

  @SerializedName("has_sound")
  private boolean hasSound;

  @SerializedName("is_ad")
  private boolean isAd;

  private boolean nsfw;

  @SerializedName("link")
  private String imageUrl;

  private List<String> tags;
  private long datetime;
  private String mp4;
  private String hls;

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

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getAccountUrl() {
    return accountUrl;
  }

  public void setAccountUrl(String accountUrl) {
    this.accountUrl = accountUrl;
  }

  public String getAdType() {
    return adType;
  }

  public void setAdType(String adType) {
    this.adType = adType;
  }

  public String getAdUrl() {
    return adUrl;
  }

  public void setAdUrl(String adUrl) {
    this.adUrl = adUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public long getViews() {
    return views;
  }

  public void setViews(long views) {
    this.views = views;
  }

  public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public int getVote() {
    return vote;
  }

  public void setVote(int vote) {
    this.vote = vote;
  }

  public int getBandwidth() {
    return bandwidth;
  }

  public void setBandwidth(int bandwidth) {
    this.bandwidth = bandwidth;
  }

  public boolean isAnimated() {
    return animated;
  }

  public void setAnimated(boolean animated) {
    this.animated = animated;
  }

  public boolean isFavorite() {
    return favorite;
  }

  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  public boolean isInGallery() {
    return inGallery;
  }

  public void setInGallery(boolean inGallery) {
    this.inGallery = inGallery;
  }

  public boolean isInMostViral() {
    return inMostViral;
  }

  public void setInMostViral(boolean inMostViral) {
    this.inMostViral = inMostViral;
  }

  public boolean isHasSound() {
    return hasSound;
  }

  public void setHasSound(boolean hasSound) {
    this.hasSound = hasSound;
  }

  public boolean isAd() {
    return isAd;
  }

  public void setAd(boolean ad) {
    isAd = ad;
  }

  public boolean isNsfw() {
    return nsfw;
  }

  public void setNsfw(boolean nsfw) {
    this.nsfw = nsfw;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public long getDatetime() {
    return datetime;
  }

  public void setDatetime(long datetime) {
    this.datetime = datetime;
  }

  public String getMp4() {
    return mp4;
  }

  public void setMp4(String mp4) {
    this.mp4 = mp4;
  }

  public String getHls() {
    return hls;
  }

  public void setHls(String hls) {
    this.hls = hls;
  }
}
