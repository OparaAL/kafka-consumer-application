package com.application.kafkaconsumer.listeners;

import com.application.kafkaconsumer.service.interfaces.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageListener {

    private final EmailSenderService emailSenderService;

    private org.slf4j.Logger Logger = LoggerFactory.getLogger(MessageListener.class);

    @KafkaListener(topics = "${kafka.topicName}")
    public void listener(String message) throws IOException {
        emailSenderService.sendEmail(message);
        Logger.info("Email was successfully sended.");
    }
}
