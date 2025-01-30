package com.fullStack.expenseTracker.controllers;

import com.fullStack.expenseTracker.dto.requests.CurrencyRequestDto;
import com.fullStack.expenseTracker.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/updatePreferredCurrency")
    public ResponseEntity<String> updatePreferredCurrency(@RequestBody CurrencyRequestDto currencyRequestDto) {
        try {
            // Call the CurrencyService to update the preferred currency
            currencyService.updatePreferredCurrency(currencyRequestDto.getCurrencyCode());
            return ResponseEntity.ok("Preferred currency updated successfully");
        } catch (IllegalArgumentException e) {
            // Handle validation error
            return ResponseEntity.badRequest().body("Invalid currency code: " + e.getMessage());
        } catch (Exception e) {
            // Handle any unexpected errors
            return ResponseEntity.status(500).body("Error updating preferred currency: " + e.getMessage());
        }
    }

    @GetMapping("/getPreferredCurrency")
    public ResponseEntity<String> getPreferredCurrency() {
        try {
            // Call the CurrencyService to retrieve the preferred currency
            String preferredCurrency = currencyService.getPreferredCurrency();
            return ResponseEntity.ok(preferredCurrency);
        } catch (Exception e) {
            // Handle any unexpected errors
            return ResponseEntity.status(500).body("Error retrieving preferred currency: " + e.getMessage());
        }
    }
}
