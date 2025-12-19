package com.okn.productInventory.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryListener {

    @SqsListener("product-notifications")
    public void listen(String message){
        System.out.println("-----------------------------------------");
        System.out.println("Message received from SQS: " + message);
        System.out.println("Aqui se puede enviar un correo por ejemplo.");
        System.out.println("-----------------------------------------");
    }
}
