package net.likelion.netflix_clone.content.entity;

import jakarta.persistence.*;
import net.likelion.netflix_clone.genre.entity.Genre;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private String thumbnailUrl;

    private String videoUrl;

    private Integer releaseYear;

    //콘텐츠와 장르 다대다 연결
    @ManyToMany
    @JoinTable(
            name = "content_genres",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    protected Content() {
    }

    public Content(
            String title,
            String description,
            String thumbnailUrl,
            String videoUrl,
            Integer releaseYear
    ) {
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.videoUrl = videoUrl;
        this.releaseYear = releaseYear;
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public void clearGenres() {
        this.genres.clear();
    }

    public void update(
            String title,
            String description,
            String thumbnailUrl,
            String videoUrl,
            Integer releaseYear
    ) {
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.videoUrl = videoUrl;
        this.releaseYear = releaseYear;
    }
}