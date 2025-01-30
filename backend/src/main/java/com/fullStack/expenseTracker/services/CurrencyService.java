package com.fullStack.expenseTracker.services;

import com.fullStack.expenseTracker.dto.requests.CurrencyRequestDto;
import com.fullStack.expenseTracker.exceptions.InvalidCurrencyCodeException;
import com.fullStack.expenseTracker.models.User;
import com.fullStack.expenseTracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyService {

    private final UserRepository userRepository;

    @Autowired
    public CurrencyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Update the preferred currency for the user.
     *
     * @param userId        the ID of the user
     * @param currencyRequestDto the request DTO containing the currency code
     * @return updated User object
     * @throws InvalidCurrencyCodeException if the currency code is invalid
     */
    public User updatePreferredCurrency(Long userId, CurrencyRequestDto currencyRequestDto) throws InvalidCurrencyCodeException {
        String currencyCode = currencyRequestDto.getCurrencyCode();

        // Validate the currency code (add more valid currency codes as necessary)
        if (!isValidCurrencyCode(currencyCode)) {
            throw new InvalidCurrencyCodeException("Invalid currency code: " + currencyCode);
        }

        // Fetch user and update the preferred currency
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPreferredCurrency(currencyCode);
            return userRepository.save(user); // Save the updated user back to the database
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    /**
     * Retrieve the preferred currency for the user.
     *
     * @param userId the ID of the user
     * @return the currency code of the user's preferred currency
     */
    public String getPreferredCurrency(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(User::getPreferredCurrency).orElse("USD"); // Default currency if not found
    }

    /**
     * Validate the provided currency code.
     *
     * @param currencyCode the currency code to validate
     * @return true if the currency code is valid, false otherwise
     */
    private boolean isValidCurrencyCode(String currencyCode) {
        // Example validation; this can be replaced with a comprehensive list of valid codes
        return currencyCode != null && currencyCode.matches("^[A-Z]{3}$");
    }
}
