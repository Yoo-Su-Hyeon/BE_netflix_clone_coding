package net.likelion.netflix_clone.content.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.likelion.netflix_clone.content.dto.ContentCreateRequest;
import net.likelion.netflix_clone.content.dto.ContentResponse;
import net.likelion.netflix_clone.content.dto.ContentUpdateRequest;
import net.likelion.netflix_clone.content.service.ContentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @Operation(summary = "콘텐츠 등록")
    @PostMapping
    public ContentResponse create(
            @RequestBody ContentCreateRequest request
    ) {
        return contentService.create(request);
    }

    @Operation(summary = "콘텐츠 조회")
    @GetMapping
    public Page<ContentResponse> findAll(

            @RequestParam(required = false)
            String keyword,

            @RequestParam(required = false)
            Long genreId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction
    ) {

        return contentService.findAll(
                keyword,
                genreId,
                page,
                size,
                sortBy,
                direction
        );
    }

    @Operation(summary = "콘텐츠 단건 조회")
    @GetMapping("/{id}")
    public ContentResponse findById(
            @PathVariable Long id
    ) {
        return contentService.findById(id);
    }

    @Operation(summary = "콘텐츠 수정")
    @PutMapping("/{id}")
    public ContentResponse update(
            @PathVariable Long id,
            @RequestBody ContentUpdateRequest request
    ) {
        return contentService.update(id, request);
    }

    @Operation(summary = "콘텐츠 삭제")
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id
    ) {
        contentService.delete(id);

        return "콘텐츠 삭제 성공";
    }
}