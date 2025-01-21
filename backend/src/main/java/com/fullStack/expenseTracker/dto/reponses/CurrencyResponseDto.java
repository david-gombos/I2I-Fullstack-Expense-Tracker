package com.fullStack.expenseTracker.dto.reponses;

import java.math.BigDecimal;
import java.util.List;

public class CurrencyResponseDto {
    
    private String preferredCurrency;
    private List<String> availableCurrencies;
    private BigDecimal conversionResult;
    private String sourceCurrency;
    private String targetCurrency;

    // Default constructor
    public CurrencyResponseDto() {}

    // Constructor with fields
    public CurrencyResponseDto(String preferredCurrency, List<String> availableCurrencies, 
                               BigDecimal conversionResult, String sourceCurrency, 
                               String targetCurrency) {
        this.preferredCurrency = preferredCurrency;
        this.availableCurrencies = availableCurrencies;
        this.conversionResult = conversionResult;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }

    // Getters and Setters
    public String getPreferredCurrency() {
        return preferredCurrency;
    }

    public void setPreferredCurrency(String preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
    }

    public List<String> getAvailableCurrencies() {
        return availableCurrencies;
    }

    public void setAvailableCurrencies(List<String> availableCurrencies) {
        this.availableCurrencies = availableCurrencies;
    }

    public BigDecimal getConversionResult() {
        return conversionResult;
    }

    public void setConversionResult(BigDecimal conversionResult) {
        this.conversionResult = conversionResult;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
