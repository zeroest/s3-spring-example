package me.zeroest.s3.example.api.controller;

import lombok.RequiredArgsConstructor;
import me.zeroest.s3.example.core.dto.FileDetail;
import me.zeroest.s3.example.core.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping(produces = {
            MimeTypeUtils.IMAGE_JPEG_VALUE,
            MimeTypeUtils.IMAGE_PNG_VALUE,
            MimeTypeUtils.IMAGE_GIF_VALUE
    })
    public ResponseEntity<byte[]> download(
            @RequestParam String key
    ) throws IOException {
        return ResponseEntity.ok(fileService.download(key));
    }

    @GetMapping("presigned")
    public void downloadPresigned(
            HttpServletResponse response,
            @RequestParam String key
    ) throws IOException {
        String presignedPath = fileService.getPresignedPath(key);
        response.sendRedirect(presignedPath);
    }

    @PostMapping
    public ResponseEntity<FileDetail> upload(
            @RequestPart("file") MultipartFile multipartFile
    ) {
        return ResponseEntity.ok(fileService.upload(multipartFile));
    }

}
