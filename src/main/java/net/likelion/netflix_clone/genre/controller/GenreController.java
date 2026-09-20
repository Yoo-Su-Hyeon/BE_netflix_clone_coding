package net.likelion.netflix_clone.genre.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.likelion.netflix_clone.genre.dto.GenreCreateRequest;
import net.likelion.netflix_clone.genre.dto.GenreResponse;
import net.likelion.netflix_clone.genre.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @Operation(summary = "장르 등록")
    @PostMapping
    public GenreResponse create(
            @RequestBody GenreCreateRequest request
    ) {
        return genreService.create(request);
    }

    @Operation(summary = "장르 전체 조회")
    @GetMapping
    public List<GenreResponse> findAll() {
        return genreService.findAll();
    }
}