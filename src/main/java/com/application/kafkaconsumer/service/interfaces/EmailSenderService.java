package com.application.kafkaconsumer.service.interfaces;

import java.io.IOException;

public interface EmailSenderService {
    void sendEmail(String email) throws IOException;
}
