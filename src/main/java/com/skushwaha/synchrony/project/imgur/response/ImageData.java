package com.skushwaha.synchrony.project.imgur.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ImageData implements Serializable {
  @JsonProperty("id")
  private String imageHash;

  @JsonProperty("deletehash")
  private String deleteHash;

  @JsonProperty("account_id")
  private String accountId;

  @JsonProperty("account_url")
  private String accountUrl;

  @JsonProperty("ad_type")
  private String adType;

  @JsonProperty("ad_url")
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

  @JsonProperty("in_gallery")
  private boolean inGallery;

  @JsonProperty("in_most_viral")
  private boolean inMostViral;

  @JsonProperty("has_sound")
  private boolean hasSound;

  @JsonProperty("is_ad")
  private boolean isAd;

  private boolean nsfw;

  @JsonProperty("link")
  private String imageUrl;

  private List<String> tags;
  private long datetime;
  private String mp4;
  private String hls;
}
