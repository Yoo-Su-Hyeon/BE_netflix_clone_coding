package net.likelion.netflix_clone.genre.repository;

import net.likelion.netflix_clone.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository
        extends JpaRepository<Genre, Long> {
}