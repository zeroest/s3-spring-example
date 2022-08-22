package me.zeroest.s3.example.core.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zeroest.s3.example.core.util.S3FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Service {

    @Getter
    @Value("${aws.s3.bucket.name}")
    private String BUCKET_NAME;

    private final AmazonS3 amazonS3;

    public S3Object getObject(String key, String format) {
        String path = S3FileUtil.createPath(key, format);
        GetObjectRequest getObjectRequest = new GetObjectRequest(BUCKET_NAME, path);
        return amazonS3.getObject(getObjectRequest);
    }

    public URL getPresignedURL(String key, String format) {
        String path = S3FileUtil.createPath(key, format);
        URL presignedUrl = amazonS3.generatePresignedUrl(BUCKET_NAME, path, Date.valueOf(LocalDate.now().plus(1L, ChronoUnit.DAYS)));
        return presignedUrl;
    }

    public PutObjectResult putObject(String fullPath, MultipartFile multipartFile) {
        File file = new File(S3FileUtil.getLocalHomeDirectory(), fullPath);
        S3FileUtil.validFileFolder(file);

        try {
            multipartFile.transferTo(file);
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, fullPath, file);
//            .withCannedAcl(CannedAccessControlList.PublicRead);
            return amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
