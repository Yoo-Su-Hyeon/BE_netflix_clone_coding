package net.likelion.netflix_clone.favorite.dto;

import net.likelion.netflix_clone.content.entity.Content;

public class FavoriteResponse {

    private Long contentId;
    private String title;
    private String description;
    private String thumbnailUrl;
    private Integer releaseYear;

    public FavoriteResponse(Content content) {
        this.contentId = content.getId();
        this.title = content.getTitle();
        this.description = content.getDescription();
        this.thumbnailUrl = content.getThumbnailUrl();
        this.releaseYear = content.getReleaseYear();
    }

    public Long getContentId() {
        return contentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }
}