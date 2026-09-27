package net.likelion.netflix_clone.image.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {

    private final Path uploadPath =
            Paths.get(System.getProperty("user.dir"), "uploads")
                    .toAbsolutePath()
                    .normalize();

    public String saveImage(MultipartFile file) {

        try {
            // uploads 폴더 없으면 생성
            Files.createDirectories(uploadPath);

            String originalFilename = file.getOriginalFilename();

            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(
                        originalFilename.lastIndexOf(".")
                );
            }

            // 파일명 중복 방지
            String savedFilename =
                    UUID.randomUUID() + extension;

            Path filePath =
                    uploadPath.resolve(savedFilename);

            // 실제 이미지 저장
            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return "/uploads/" + savedFilename;

        } catch (IOException e) {
            throw new RuntimeException(
                    "이미지 저장에 실패했습니다.",
                    e
            );
        }
    }
}