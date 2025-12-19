package com.okn.productInventory.service;

import io.awspring.cloud.s3.S3Template;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SqsTemplate sqsTemplate;

    @Value("${app.sqs.queue-url}")
    private String queueUrl;

    public NotificationService(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }


    public void sendNotification(String message){
        sqsTemplate.send(queueUrl, message);
    }
}
