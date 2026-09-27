package net.likelion.netflix_clone.favorite.service;

import net.likelion.netflix_clone.content.entity.Content;
import net.likelion.netflix_clone.content.repository.ContentRepository;
import net.likelion.netflix_clone.favorite.dto.FavoriteResponse;
import net.likelion.netflix_clone.favorite.entity.Favorite;
import net.likelion.netflix_clone.favorite.repository.FavoriteRepository;
import net.likelion.netflix_clone.user.entity.User;
import net.likelion.netflix_clone.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    public FavoriteService(
            FavoriteRepository favoriteRepository,
            UserRepository userRepository,
            ContentRepository contentRepository
    ) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
    }

    // 찜하기
    @Transactional
    public void addFavorite(String email, Long contentId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("콘텐츠를 찾을 수 없습니다."));

        if (favoriteRepository.existsByUserAndContent(user, content)) {
            throw new IllegalArgumentException("이미 찜한 콘텐츠입니다.");
        }

        Favorite favorite = new Favorite(user, content);

        favoriteRepository.save(favorite);
    }

    // 찜 취소
    @Transactional
    public void removeFavorite(String email, Long contentId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("콘텐츠를 찾을 수 없습니다."));

        Favorite favorite = favoriteRepository.findByUserAndContent(user, content)
                .orElseThrow(() -> new IllegalArgumentException("찜한 콘텐츠가 아닙니다."));

        favoriteRepository.delete(favorite);
    }

    // 내 찜 목록 조회
    public List<FavoriteResponse> getMyFavorites(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return favoriteRepository.findAllByUser(user)
                .stream()
                .map(favorite -> new FavoriteResponse(favorite.getContent()))
                .toList();
    }
}