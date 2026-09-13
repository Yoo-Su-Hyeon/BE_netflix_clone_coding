package net.likelion.netflix_clone.content.dto;

public class ContentUpdateRequest {

    private String title;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private Integer releaseYear;

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