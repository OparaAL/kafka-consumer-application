package com.application.kafkaconsumer.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageListener {

    private org.slf4j.Logger Logger = LoggerFactory.getLogger(MessageListener.class);

    @KafkaListener(topics = "${kafka.topicName}")
    public void listenGroupFoo(String message) {
        Logger.info("Received message from kafka: " + message);
    }
}
