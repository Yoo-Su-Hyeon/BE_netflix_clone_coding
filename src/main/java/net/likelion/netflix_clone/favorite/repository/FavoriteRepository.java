package net.likelion.netflix_clone.favorite.repository;

import net.likelion.netflix_clone.content.entity.Content;
import net.likelion.netflix_clone.favorite.entity.Favorite;
import net.likelion.netflix_clone.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    boolean existsByUserAndContent(User user, Content content);

    Optional<Favorite> findByUserAndContent(User user, Content content);

    List<Favorite> findAllByUser(User user);
}