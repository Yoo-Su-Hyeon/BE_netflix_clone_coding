package net.likelion.netflix_clone.favorite.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.likelion.netflix_clone.favorite.dto.FavoriteResponse;
import net.likelion.netflix_clone.favorite.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @Operation(summary = "콘텐츠 찜하기")
    @PostMapping("/{contentId}")
    public ResponseEntity<String> addFavorite(
            @PathVariable Long contentId,
            Authentication authentication
    ) {
        String email = authentication.getName();

        favoriteService.addFavorite(email, contentId);

        return ResponseEntity.ok("찜하기 완료");
    }

    @Operation(summary = "콘텐츠 찜 취소")
    @DeleteMapping("/{contentId}")
    public ResponseEntity<String> removeFavorite(
            @PathVariable Long contentId,
            Authentication authentication
    ) {
        String email = authentication.getName();

        favoriteService.removeFavorite(email, contentId);

        return ResponseEntity.ok("찜 취소 완료");
    }

    @Operation(summary = "내 찜 목록 조회")
    @GetMapping
    public ResponseEntity<List<FavoriteResponse>> getMyFavorites(
            Authentication authentication
    ) {
        String email = authentication.getName();

        return ResponseEntity.ok(
                favoriteService.getMyFavorites(email)
        );
    }
}