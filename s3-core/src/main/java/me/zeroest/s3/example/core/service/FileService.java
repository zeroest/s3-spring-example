package me.zeroest.s3.example.core.service;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import me.zeroest.s3.example.core.domain.s3file.FileMetadataService;
import me.zeroest.s3.example.core.dto.FileDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileMetadataService fileMetadataService;
    private final S3Service s3Service;

    public byte[] download(String key) throws IOException {
        FileDetail fileDetail = fileMetadataService.findById(key);
        S3Object object = s3Service.getObject(fileDetail.getId(), fileDetail.getFormat());
        return IOUtils.toByteArray(object.getObjectContent());
    }

    public FileDetail upload(MultipartFile multipartFile) {
        FileDetail fileDetail = FileDetail.of(multipartFile);
        s3Service.putObject(fileDetail.getPath(), multipartFile);
        fileMetadataService.save(fileDetail);
        return fileDetail;
    }

}
