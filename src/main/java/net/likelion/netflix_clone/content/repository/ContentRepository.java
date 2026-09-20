package net.likelion.netflix_clone.content.repository;

import net.likelion.netflix_clone.content.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository
        extends JpaRepository<Content, Long> {

    Page<Content> findByTitleContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );

    Page<Content> findByGenresId(
            Long genreId,
            Pageable pageable
    );

    Page<Content> findByTitleContainingIgnoreCaseAndGenresId(
            String keyword,
            Long genreId,
            Pageable pageable
    );
}