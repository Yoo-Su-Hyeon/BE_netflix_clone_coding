package net.likelion.netflix_clone.content.repository;

import net.likelion.netflix_clone.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository
        extends JpaRepository<Content, Long> {
}