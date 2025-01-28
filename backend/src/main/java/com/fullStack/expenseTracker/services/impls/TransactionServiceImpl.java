package com.fullStack.expenseTracker.services.impls;

import com.fullStack.expenseTracker.dto.requests.TransactionRequestDto;
import com.fullStack.expenseTracker.enums.ECurrencyType;
import com.fullStack.expenseTracker.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionServiceImpl {

    @Autowired
    private CurrencyService currencyService;

    /**
     * This function handles the addition of transactions while considering the selected currency type.
     * It ensures that all amounts are converted and stored properly based on the currency selected by the user.
     * This function is invoked when a user submits a transaction with the specified currency.
     *
     * @param transactionRequestDto the DTO containing transaction details, including amount and currency
     * @throws IllegalArgumentException if the currency code is invalid or amount is negative
     */
    @Transactional
    public void addTransaction(TransactionRequestDto transactionRequestDto) {
        validateTransactionRequest(transactionRequestDto);

        BigDecimal amountInBaseCurrency = convertToBaseCurrency(transactionRequestDto.getAmount(), transactionRequestDto.getCurrencyCode());

        // Logic to save the transaction to the database
        
        // For example:
        // Transaction transaction = new Transaction();
        // transaction.setAmount(amountInBaseCurrency);
        // transaction.setCurrency(ECurrencyType.valueOf(transactionRequestDto.getCurrencyCode()));
        // transactionRepository.save(transaction);
    }

    /**
     * Validates the transaction request.
     *
     * @param transactionRequestDto the DTO containing transaction details
     * @throws IllegalArgumentException if the currency code is invalid or amount is negative
     */
    private void validateTransactionRequest(TransactionRequestDto transactionRequestDto) {
        if (transactionRequestDto.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative.");
        }
        
        Optional<ECurrencyType> currencyType = currencyService.getAvailableCurrencies().stream()
                            .filter(currency -> currency.name().equals(transactionRequestDto.getCurrencyCode()))
                            .findFirst();
        
        if (!currencyType.isPresent()) {
            throw new IllegalArgumentException("Invalid currency code: " + transactionRequestDto.getCurrencyCode());
        }
    }

    /**
     * Converts the given amount from the specified currency to the base currency.
     *
     * @param amount the amount to convert
     * @param currencyCode the currency code from which to convert
     * @return the amount converted to the base currency
     */
    private BigDecimal convertToBaseCurrency(BigDecimal amount, String currencyCode) {
        // Placeholder for conversion logic, typically involves fetching exchange rates

        // Example: Assuming a fixed conversion rate for simplification
        BigDecimal conversionRate = currencyService.getConversionRate(currencyCode);
        return amount.multiply(conversionRate);
    }
}
