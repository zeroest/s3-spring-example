package me.zeroest.s3.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "me.zeroest.s3.example")
@SpringBootApplication
public class S3Application {
    public static void main(String[] args) {
        SpringApplication.run(S3Application.class, args);
    }
}
