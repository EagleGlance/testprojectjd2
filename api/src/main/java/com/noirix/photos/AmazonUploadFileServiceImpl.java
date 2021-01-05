package com.noirix.photos;

import com.noirix.config.AmazonConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmazonUploadFileServiceImpl implements AmazonUploadFileService {

    private final S3Client s3Client;

    private final AmazonConfig amazonS3Config;

    @Override
    public String uploadFile(byte[] imageBytes, Long userId) {

        String imageUUID = UUID.randomUUID().toString();

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(amazonS3Config.getBucket())
                        .contentType("image/jpeg")
                        .contentLength((long) imageBytes.length)
                        .acl(ObjectCannedACL.PUBLIC_READ)
                        .key(String.format("%s/%s/%s.jpg", amazonS3Config.getUserFolder(), userId, imageUUID))
                        .build(),
                RequestBody.fromBytes(imageBytes)
        );

        return String.format("%s/%s/%s/%s/%s.jpg",
                amazonS3Config.getServerUrl(),
                amazonS3Config.getBucket(),
                amazonS3Config.getUserFolder(),
                userId, imageUUID);
    }
}
