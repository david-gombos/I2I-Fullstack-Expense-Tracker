package com.fullStack.expenseTracker.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * CurrencyRequestDto is a Data Transfer Object that encapsulates the structure
 * of the incoming request to change the preferred currency. It includes
 * validation to ensure that the currency code is correctly specified.
 */
public class CurrencyRequestDto {

    @NotBlank(message = "Currency code must not be blank")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code must be a valid ISO 4217 format (3 uppercase letters)")
    private String currencyCode;

    public CurrencyRequestDto() {
    }

    public CurrencyRequestDto(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
