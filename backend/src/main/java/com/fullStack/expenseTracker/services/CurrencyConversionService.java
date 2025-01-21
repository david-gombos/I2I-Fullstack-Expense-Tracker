package com.fullStack.expenseTracker.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CurrencyConversionService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private final Map<String, Double> cachedRates = new ConcurrentHashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * This function will retrieve the latest exchange rates from a reliable currency conversion API.
     * It will manage caching of rates to optimize performance and reduce API calls.
     *
     * @param baseCurrency The currency from which to convert.
     * @return Map of available currencies and their exchange rates against the base currency.
     */
    public Map<String, Double> fetchExchangeRate(String baseCurrency) {
        // Check if the rates for this base currency are already cached
        if (cachedRates.containsKey(baseCurrency)) {
            logger.info("Fetching rates from cache for base currency: {}", baseCurrency);
            return new HashMap<>(cachedRates);
        }
        
        // Fetch new rates from the external API
        try {
            String response = restTemplate.getForObject(API_URL + baseCurrency, String.class);
            // Assume parsed response is a Map<String, Double> { currency -> rate }
            // You would normally use a JSON library to parse this response
            Map<String, Double> rates = parseRatesFromApiResponse(response);
            
            // Cache the rates for performance optimization
            cachedRates.putAll(rates);
            logger.info("Fetched and cached new rates for base currency: {}", baseCurrency);
            return rates;
        } catch (Exception e) {
            logger.error("Error fetching exchange rates for {}: {}", baseCurrency, e.getMessage());
            throw new RuntimeException("Failed to fetch exchange rates", e);
        }
    }

    /**
     * This function will log details of currency conversions for auditing and analytics purposes.
     * The logs may include user ID, source currency, target currency, and conversion amount.
     *
     * @param userId  The ID of the user performing the conversion.
     * @param fromCurrency The source currency.
     * @param toCurrency   The target currency.
     * @param amount      The amount to convert.
     */
    public void logCurrencyConversion(String userId, String fromCurrency, String toCurrency, double amount) {
        logger.info("User ID: {}, Converting {} from {} to {}", userId, amount, fromCurrency, toCurrency);
        // Here you can implement additional logging mechanisms (e.g., saving to a database)
    }

    private Map<String, Double> parseRatesFromApiResponse(String response) {
        // Implement a JSON parser to extract required rates from the API response.
        // For the purpose of this example, we're returning an empty map.
        return new HashMap<>();
    }
}
