package com.fullStack.expenseTracker.controllers;

import com.fullStack.expenseTracker.dto.requests.CurrencyRequestDto;
import com.fullStack.expenseTracker.dto.responses.CurrencyResponseDto;
import com.fullStack.expenseTracker.services.CurrencyService;
import com.fullStack.expenseTracker.services.CurrencyConversionService;
import com.fullStack.expenseTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private UserService userService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    // Endpoint to get the preferred currency of the user
    @GetMapping("/preferred")
    public ResponseEntity<String> getPreferredCurrency() {
        String preferredCurrency = userService.getPreferredCurrency();
        return ResponseEntity.ok(preferredCurrency);
    }

    // Endpoint to get available currencies
    @GetMapping("/available")
    public ResponseEntity<List<String>> getAvailableCurrencies() {
        currencyService.logCurrencyServiceCall(); // Log call to the currency service
        List<String> availableCurrencies = currencyService.fetchAvailableCurrencies();
        return ResponseEntity.ok(availableCurrencies);
    }

    // Endpoint to convert currency
    @PostMapping("/convert")
    public ResponseEntity<CurrencyResponseDto> convertCurrency(@RequestBody CurrencyRequestDto requestDto) {
        if (requestDto == null || requestDto.getAmount() == null || requestDto.getFromCurrency() == null || requestDto.getToCurrency() == null) {
            return ResponseEntity.badRequest().body(null); // Respond with bad request if input is invalid
        }

        // Fetch the exchange rate
        double exchangeRate = currencyConversionService.fetchExchangeRate(requestDto.getFromCurrency(), requestDto.getToCurrency());
        if (exchangeRate <= 0) {
            return ResponseEntity.badRequest().body(null); // Invalid exchange rate
        }

        double convertedAmount = requestDto.getAmount() * exchangeRate;
        CurrencyResponseDto responseDto = new CurrencyResponseDto(requestDto.getFromCurrency(), requestDto.getToCurrency(), requestDto.getAmount(), convertedAmount);

        // Log the currency conversion for auditing purposes
        currencyConversionService.logCurrencyConversion(requestDto.getFromCurrency(), requestDto.getToCurrency(), requestDto.getAmount(), convertedAmount);
        
        return ResponseEntity.ok(responseDto);
    }
}
