package com.fullStack.expenseTracker.services;

import java.util.ArrayList;
import java.util.List;

public class CurrencyService {

    // Summary: This function fetches a list of all available currencies for selection by the user. 
    // It can potentially retrieve this information from a remote currency API or a predefined set.
    // This function will be called from the user interface to populate the list of currency options when making a transaction.
    public List<String> getAllCurrencies() {
        List<String> currencies = new ArrayList<>();

        // Here we populate the currencies list with example currency codes.
        // In a real application, you would likely make an API call to a currency service or fetch from a database.
        currencies.add("USD"); // United States Dollar
        currencies.add("EUR"); // Euro
        currencies.add("GBP"); // British Pound
        currencies.add("JPY"); // Japanese Yen
        currencies.add("AUD"); // Australian Dollar
        currencies.add("CAD"); // Canadian Dollar
        currencies.add("CHF"); // Swiss Franc
        currencies.add("CNY"); // Chinese Yuan
        currencies.add("INR"); // Indian Rupee
        
        // Additional currencies can be added as needed.

        return currencies;
    }
}
