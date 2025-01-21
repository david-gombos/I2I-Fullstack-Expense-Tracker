package com.fullStack.expenseTracker.dto.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * CurrencyRequestDto serves as a Data Transfer Object for carrying requests related to 
 * currency information, including the currency type and the amount to convert.
 */
public class CurrencyRequestDto {

    @NotEmpty(message = "Currency type cannot be empty")
    private String currencyType;

    @NotNull(message = "Amount to convert cannot be null")
    @Positive(message = "Amount to convert must be a positive number")
    private Double amount;

    // Constructors
    public CurrencyRequestDto() {
    }

    public CurrencyRequestDto(String currencyType, Double amount) {
        this.currencyType = currencyType;
        this.amount = amount;
    }

    // Getters and Setters
    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
