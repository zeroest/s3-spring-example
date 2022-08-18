package me.zeroest.s3.example.api.controller;

import lombok.RequiredArgsConstructor;
import me.zeroest.s3.example.core.dto.FileDetail;
import me.zeroest.s3.example.core.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping(produces = {MimeTypeUtils.IMAGE_PNG_VALUE})
    public byte[] download(
            @RequestParam String key,
            @RequestParam String format
    ) throws IOException {
        return fileService.download(key, format);
    }

    @PostMapping
    public ResponseEntity<FileDetail> upload(
            @RequestPart("file") MultipartFile multipartFile
    ) {
        return ResponseEntity.ok(fileService.upload(multipartFile));
    }

}
