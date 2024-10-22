package com.example.currencyconverter.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConverterService {
   private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public double convert(double amount, String fromCurrency, String toCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + fromCurrency;

        // Call the exchange rate API to get the rates
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);
        
        if (response != null && response.getRates().containsKey(toCurrency)) {
            double rate = response.getRates().get(toCurrency);
            return amount * rate;
        } else {
            throw new IllegalArgumentException("Invalid target currency");
        }
    }
}
