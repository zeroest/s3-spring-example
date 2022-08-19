package me.zeroest.s3.example.core.domain.s3file;

import lombok.*;
import me.zeroest.s3.example.core.dto.FileDetail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "file_metadata")
@Getter
@Entity
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileMetadata {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "format")
    private String format;

    @Column(name = "path")
    private String path;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "bytes")
    private long bytes;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public static FileMetadata from(FileDetail fileDetail) {
        return FileMetadata.builder()
                .id(fileDetail.getId())
                .name(fileDetail.getName())
                .format(fileDetail.getFormat())
                .path(fileDetail.getPath())
                .width(fileDetail.getWidth())
                .height(fileDetail.getHeight())
                .bytes(fileDetail.getBytes())
                .createdAt(fileDetail.getCreatedAt())
                .build();
    }
}
