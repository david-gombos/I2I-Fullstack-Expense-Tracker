package com.fullStack.expenseTracker.controllers;

import com.fullStack.expenseTracker.services.CurrencyService;
import com.fullStack.expenseTracker.dto.responses.CurrencyResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    /**
     * Summary: This function acts as an endpoint to retrieve all the available currency types for a user
     * to choose from when making a transaction. This function is called by the frontend application to
     * show users their currency selection options.
     * 
     * @return ResponseEntity<List<CurrencyResponseDto>> - A response entity containing a list of currency options.
     */
    @GetMapping("/available")
    public ResponseEntity<List<CurrencyResponseDto>> getAvailableCurrencyOptions() {
        try {
            List<CurrencyResponseDto> currencies = currencyService.getAllCurrencies();
            if (currencies.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(currencies);
        } catch (Exception e) {
            // Log the error (using a logger is a good practice in production code)
            // logger.error("Error fetching currency options", e);
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
