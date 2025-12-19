package com.okn.productInventory.service;

import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.endpoints.internal.Template;

import java.io.IOException;
import java.io.InputStream;

@Service
public class S3Service {

    private final S3Template s3Template;

    @Value("${app.s3.bucket-name}")
    private String bucketName;

    public S3Service(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    public String uploadFile(String fileName, InputStream inputStream) throws IOException {
        var resource = s3Template.upload(bucketName, fileName, inputStream);
        return resource.getURL().toString();
    }
}
