package net.likelion.netflix_clone.content.dto;

import java.util.Set;

public class ContentCreateRequest {

    private String title;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private Integer releaseYear;
    private Set<Long> genreIds;

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

    public Set<Long> getGenreIds() {
        return genreIds;
    }
}