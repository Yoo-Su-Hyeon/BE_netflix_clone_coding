package net.likelion.netflix_clone.favorite.entity;

import jakarta.persistence.*;
import net.likelion.netflix_clone.content.entity.Content;
import net.likelion.netflix_clone.user.entity.User;

@Entity
@Table(
        name = "favorites",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_favorite_user_content",
                        columnNames = {"user_id", "content_id"}
                )
        }
)
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;

    protected Favorite() {
    }

    public Favorite(User user, Content content) {
        this.user = user;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Content getContent() {
        return content;
    }
}