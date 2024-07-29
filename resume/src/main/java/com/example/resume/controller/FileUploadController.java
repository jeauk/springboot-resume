package com.example.resume.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
 
@RestController
@CrossOrigin
@RequestMapping("/resume")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "파일이 비어있습니다.";
            }
            String originalFileName = file.getOriginalFilename();
            String sanitizedFileName = originalFileName.replaceAll("\\s+", "");

            Path copyLocation = Paths.get(uploadDir).resolve(sanitizedFileName);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            return "/resume/img/" + sanitizedFileName; // 파일 경로 반환
        } catch (Exception e) {
            logger.error("파일 업로드 오류", e);
            return "파일 업로드 중 오류가 발생했습니다.";
        }
    }
}
