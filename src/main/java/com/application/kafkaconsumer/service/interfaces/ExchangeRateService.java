package com.application.kafkaconsumer.service.interfaces;

import com.application.kafkaconsumer.model.Currency;

import java.io.IOException;

public interface ExchangeRateService {
    String getExchangeRate(Currency from, Currency to) throws IOException;
}
