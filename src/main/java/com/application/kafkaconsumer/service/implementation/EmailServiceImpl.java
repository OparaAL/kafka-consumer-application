package com.application.kafkaconsumer.service.implementation;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.application.kafkaconsumer.model.Currency;
import com.application.kafkaconsumer.service.interfaces.EmailSenderService;
import com.application.kafkaconsumer.service.interfaces.ExchangeRateService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailSenderService {

    @Value("${aws.ses.template.email}")
    private String emailTemplate;

    @Value("${aws.ses.fromEmail}")
    private String fromEmail;

    private final AmazonSimpleEmailService client;
    private final ExchangeRateService exchangeRateService;

    public void sendEmail(String email) throws IOException {
        Destination destination = new Destination();
        destination.setToAddresses(List.of(email));

        SendTemplatedEmailRequest templateEmail = new SendTemplatedEmailRequest();
        templateEmail.withDestination(destination);
        templateEmail.withSource(fromEmail);
        templateEmail.withTemplateData(templateData(email));
        templateEmail.withTemplate(emailTemplate);
        client.sendTemplatedEmail(templateEmail);
    }

    private String templateData(String email) throws IOException {
        JsonObject ret = new JsonObject();
        ret.addProperty("email", email);
        ret.addProperty("dollar", exchangeRateService.getExchangeRate(Currency.USD, Currency.UAH));
        ret.addProperty("euro", exchangeRateService.getExchangeRate(Currency.EUR, Currency.UAH));
        return ret.toString();
    }


    private static class TemplateData {
        private final JsonObject json = new JsonObject();

        public TemplateData add(@NonNull String property, @Nullable Object value) {
            json.addProperty(property, value != null ? String.valueOf(value) : "");
            return this;
        }

        @Override
        public String toString() {
            return json.toString();
        }
    }
}