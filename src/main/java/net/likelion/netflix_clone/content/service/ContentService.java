package net.likelion.netflix_clone.content.service;

import net.likelion.netflix_clone.content.dto.ContentCreateRequest;
import net.likelion.netflix_clone.content.dto.ContentResponse;
import net.likelion.netflix_clone.content.dto.ContentUpdateRequest;
import net.likelion.netflix_clone.content.entity.Content;
import net.likelion.netflix_clone.content.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
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

        Content savedContent =
                contentRepository.save(content);

        return new ContentResponse(savedContent);
    }

    // READ - 전체 조회
    @Transactional(readOnly = true)
    public List<ContentResponse> findAll() {

        return contentRepository.findAll()
                .stream()
                .map(ContentResponse::new)
                .toList();
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