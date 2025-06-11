package com.project.billingSoftware.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI; // Import URI for endpointOverride

@Configuration
public class AWSConfig {

    // --- AWS S3 Properties ---
    @Value("${aws.access.key}")
    private String awsAccessKey;
    @Value("${aws.secret.key}")
    private String awsSecretKey;
    @Value("${aws.region}")
    private String awsRegion;

    // --- MinIO Specific Properties ---
    @Value("${s3.use-local}") // Flag to enable/disable local S3 (MinIO)
    private boolean useLocalS3;

    @Value("${minio.endpoint}")
    private String minioEndpoint;
    @Value("${minio.access-key}")
    private String minioAccessKey;
    @Value("${minio.secret-key}")
    private String minioSecretKey;

    @Bean
    public S3Client S3Client() {
        if (useLocalS3) {
            // Configure S3Client for MinIO
            return S3Client.builder()
                    .endpointOverride(URI.create(minioEndpoint)) // Point to your local MinIO
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(minioAccessKey, minioSecretKey)))
                    .region(Region.US_EAST_1) // Region is required by SDK, but its value is arbitrary for MinIO
                    .forcePathStyle(true) // Crucial for MinIO compatibility
                    .build();
        } else {
            // Configure S3Client for AWS S3
            return S3Client.builder()
                    .region(Region.of(awsRegion))
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(awsAccessKey, awsSecretKey)))
                    .build();
        }
    }
}