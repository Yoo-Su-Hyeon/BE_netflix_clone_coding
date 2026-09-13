package net.likelion.netflix_clone.content.dto;

import net.likelion.netflix_clone.content.entity.Content;

public class ContentResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final String thumbnailUrl;
    private final String videoUrl;
    private final Integer releaseYear;

    public ContentResponse(Content content) {
        this.id = content.getId();
        this.title = content.getTitle();
        this.description = content.getDescription();
        this.thumbnailUrl = content.getThumbnailUrl();
        this.videoUrl = content.getVideoUrl();
        this.releaseYear = content.getReleaseYear();
    }

    public Long getId() {
        return id;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }
}