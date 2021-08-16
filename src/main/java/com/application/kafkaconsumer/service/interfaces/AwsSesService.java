package com.application.kafkaconsumer.service.interfaces;

public interface AwsSesService {
    void sendEmail(String message);
}
