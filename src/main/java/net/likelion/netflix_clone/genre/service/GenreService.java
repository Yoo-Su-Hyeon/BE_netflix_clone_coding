package net.likelion.netflix_clone.genre.service;

import net.likelion.netflix_clone.genre.dto.GenreCreateRequest;
import net.likelion.netflix_clone.genre.dto.GenreResponse;
import net.likelion.netflix_clone.genre.entity.Genre;
import net.likelion.netflix_clone.genre.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // CREATE - 장르 등록
    @Transactional
    public GenreResponse create(
            GenreCreateRequest request
    ) {

        Genre genre = new Genre(
                request.getName()
        );

        Genre savedGenre =
                genreRepository.save(genre);

        return new GenreResponse(savedGenre);
    }

    // READ - 장르 전체 조회
    @Transactional(readOnly = true)
    public List<GenreResponse> findAll() {

        return genreRepository.findAll()
                .stream()
                .map(GenreResponse::new)
                .toList();
    }
}