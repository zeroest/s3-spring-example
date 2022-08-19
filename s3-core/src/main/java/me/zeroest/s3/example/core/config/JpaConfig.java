package me.zeroest.s3.example.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("me.zeroest.s3.example.*.domain.*")
@EnableJpaRepositories(basePackages = "me.zeroest.s3.example.*")
@Configuration
public class JpaConfig {
}
