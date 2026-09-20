package net.likelion.netflix_clone.genre.dto;

import net.likelion.netflix_clone.genre.entity.Genre;

public class GenreResponse {

    private final Long id;
    private final String name;

    public GenreResponse(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}