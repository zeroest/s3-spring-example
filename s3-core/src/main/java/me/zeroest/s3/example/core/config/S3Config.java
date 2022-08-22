package me.zeroest.s3.example.core.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

//    @Value("${aws.s3.accessKey}")
//    private String ACCESS_KEY;

//    @Value("${aws.s3.secretKey}")
//    private String SECRET_KEY;

//    @Bean
//    public BasicAWSCredentials awsCredentialsProvider() {
//        return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
//    }

    @Bean
    public AmazonS3 amazonS3Client() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(Region.AP_Seoul.toString())
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentialsProvider()))
                .build();
    }

}
