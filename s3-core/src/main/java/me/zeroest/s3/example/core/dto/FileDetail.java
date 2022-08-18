package me.zeroest.s3.example.core.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.zeroest.s3.example.core.util.S3FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FileDetail {
    private String id;
    private String name;
    private String format;
    private String path;
    private Integer width;
    private Integer height;
    private long bytes;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public static FileDetail of(MultipartFile multipartFile) {
        final String fileId = S3FileUtil.createFileId();
        final String format = S3FileUtil.getFormat(multipartFile.getContentType());

        return FileDetail.builder()
                .id(fileId)
                .name(multipartFile.getOriginalFilename())
                .format(format)
                .path(S3FileUtil.createPath(fileId, format))
                .bytes(multipartFile.getSize())
                .build();
    }
}
