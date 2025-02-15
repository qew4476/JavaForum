package com.java.forum.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Service
public class S3Service {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.s3.bucket.region}")
    private String region;

    @Value("${aws.s3.bucket.access.key.id}")
    private String accessKeyId;

    @Value("${aws.s3.bucket.secret.access.key}")
    private String secretAccessKey;

    private S3Client s3Client;

    // ✅ 監聽 Spring Boot 啟動完成事件，初始化 S3 客戶端
    @EventListener(ApplicationReadyEvent.class)
    public void initS3Client() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        System.out.println("S3Client 初始化完成: " + this.s3Client);
    }

    public String uploadFile(MultipartFile file, String fileName) {
        try {
            // Get input stream from the MultipartFile
            InputStream inputStream = file.getInputStream();

            // Create PutObjectRequest with file and metadata
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            // Upload file to S3
            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, file.getSize()));

            // Generate the file URL for accessing it
            return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toExternalForm();
        } catch (S3Exception | IOException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }
}
