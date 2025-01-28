package com.fullStack.expenseTracker.enums;

/**
 * ECurrencyType class defines various currency types supported by the application.
 * This includes currency codes like USD, EUR, GBP, etc.
 * These definitions are used for validating and converting amounts during 
 * transaction processing based on selected currencies.
 */
public enum ECurrencyType {
    USD("United States Dollar"),
    EUR("Euro"),
    GBP("British Pound"),
    JPY("Japanese Yen"),
    AUD("Australian Dollar"),
    CAD("Canadian Dollar"),
    CHF("Swiss Franc"),
    CNY("Chinese Yuan"),
    SEK("Swedish Krona"),
    NZD("New Zealand Dollar");

    private final String description;

    ECurrencyType(String description) {
        this.description = description;
    }

    /**
     * Getter for the currency description.
     * 
     * @return description of the currency type.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Summary: This function defines the various currency types that the application supports.
     * This can include currency codes like USD, EUR, GBP, etc.
     * This function is utilized in the transaction processing to validate 
     * or convert amounts between different currency types.
     */
    public static ECurrencyType[] defineCurrencyTypes() {
        return ECurrencyType.values();
    }
}
