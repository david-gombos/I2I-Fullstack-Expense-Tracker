package com.fullStack.expenseTracker.dto.requests;

/**
 * CurrencySelectionRequestDto is a Data Transfer Object that holds the information
 * regarding the currency selection made by the user for a transaction.
 */
public class CurrencySelectionRequestDto {
    
    private String currencyCode;

    /**
     * Retrieves the currency code selected by the user for a transaction.
     * This is used to capture the currency in which the transaction is being made.
     * This function is called during the transaction processing to ensure the correct currency is being applied.
     * 
     * @return the currency code as a String
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the currency code for this currency selection request.
     * 
     * @param currencyCode - the currency code to set
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "CurrencySelectionRequestDto{" +
                "currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
