package com.application.kafkaconsumer.service.implementation;

import com.application.kafkaconsumer.model.Currency;
import com.application.kafkaconsumer.service.interfaces.ExchangeRateService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Override
    public String getExchangeRate(Currency from, Currency to) throws IOException {
        String requestUrl = String.format("https://www.xe.com/currencyconverter/convert/?Amount=1&From=%s&To=%s", from, to);
        Document doc = Jsoup.connect(requestUrl).get();
        Elements elements = doc.getElementsByClass("result__BigRate-sc-1bsijpp-1 iGrAod");
        return Objects.requireNonNull(elements.first()).text();
    }
}
