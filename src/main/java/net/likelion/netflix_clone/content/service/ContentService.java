package net.likelion.netflix_clone.content.service;

import net.likelion.netflix_clone.content.dto.ContentCreateRequest;
import net.likelion.netflix_clone.content.dto.ContentResponse;
import net.likelion.netflix_clone.content.dto.ContentUpdateRequest;
import net.likelion.netflix_clone.content.entity.Content;
import net.likelion.netflix_clone.content.repository.ContentRepository;
import net.likelion.netflix_clone.genre.entity.Genre;
import net.likelion.netflix_clone.genre.repository.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final GenreRepository genreRepository;

    public ContentService(
            ContentRepository contentRepository,
            GenreRepository genreRepository
    ) {
        this.contentRepository = contentRepository;
        this.genreRepository = genreRepository;
    }

    // CREATE
    @Transactional
    public ContentResponse create(
            ContentCreateRequest request
    ) {

        Content content = new Content(
                request.getTitle(),
                request.getDescription(),
                request.getThumbnailUrl(),
                request.getVideoUrl(),
                request.getReleaseYear()
        );

        if (request.getGenreIds() != null) {

            List<Genre> genres =
                    genreRepository.findAllById(
                            request.getGenreIds()
                    );

            genres.forEach(content::addGenre);
        }

        Content savedContent =
                contentRepository.save(content);

        return new ContentResponse(savedContent);
    }

    // READ - 검색 + 장르 + 페이징 + 정렬
    @Transactional(readOnly = true)
    public Page<ContentResponse> findAll(
            String keyword,
            Long genreId,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable =
                PageRequest.of(page, size, sort);

        Page<Content> contents;

        if (keyword != null
                && !keyword.isBlank()
                && genreId != null) {

            contents =
                    contentRepository
                            .findByTitleContainingIgnoreCaseAndGenresId(
                                    keyword,
                                    genreId,
                                    pageable
                            );

        } else if (keyword != null
                && !keyword.isBlank()) {

            contents =
                    contentRepository
                            .findByTitleContainingIgnoreCase(
                                    keyword,
                                    pageable
                            );

        } else if (genreId != null) {

            contents =
                    contentRepository
                            .findByGenresId(
                                    genreId,
                                    pageable
                            );

        } else {

            contents =
                    contentRepository.findAll(pageable);
        }

        return contents.map(ContentResponse::new);
    }

    // READ - 단건 조회
    @Transactional(readOnly = true)
    public ContentResponse findById(Long id) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "존재하지 않는 콘텐츠입니다."
                        )
                );

        return new ContentResponse(content);
    }

    // UPDATE
    @Transactional
    public ContentResponse update(
            Long id,
            ContentUpdateRequest request
    ) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "존재하지 않는 콘텐츠입니다."
                        )
                );

        content.update(
                request.getTitle(),
                request.getDescription(),
                request.getThumbnailUrl(),
                request.getVideoUrl(),
                request.getReleaseYear()
        );

        if (request.getGenreIds() != null) {

            content.clearGenres();

            List<Genre> genres =
                    genreRepository.findAllById(
                            request.getGenreIds()
                    );

            genres.forEach(content::addGenre);
        }

        return new ContentResponse(content);
    }

    // DELETE
    @Transactional
    public void delete(Long id) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "존재하지 않는 콘텐츠입니다."
                        )
                );

        contentRepository.delete(content);
    }
}