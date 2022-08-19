package me.zeroest.s3.example.core.domain.s3file;

import lombok.RequiredArgsConstructor;
import me.zeroest.s3.example.core.dto.FileDetail;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileMetadataService {
    private final FileMetadataRepository fileMetadataRepository;

    public void save(FileDetail fileDetail) {
        FileMetadata fileMetadata = FileMetadata.from(fileDetail);
        fileMetadataRepository.save(fileMetadata);
    }

    public FileDetail findById(String id) {
        Optional<FileMetadata> fileMetadataOp = fileMetadataRepository.findById(id);
        if (fileMetadataOp.isEmpty()) {
            throw new IllegalArgumentException("Not exist file metadata");
        }

        return FileDetail.from(fileMetadataOp.get());
    }
}
