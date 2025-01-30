package com.fullStack.expenseTracker.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.fullStack.expenseTracker.models.User;
import com.fullStack.expenseTracker.repositories.UserRepository;
import com.fullStack.expenseTracker.services.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CurrencyServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CurrencyService currencyService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setPreferredCurrency("USD");
    }

    @Test
    public void testUpdatePreferredCurrency() {
        String newCurrencyCode = "EUR";
        
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<?> response = currencyService.updatePreferredCurrency(user.getId(), newCurrencyCode);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("EUR", user.getPreferredCurrency());
        verify(userRepository).save(user);
    }

    @Test
    public void testUpdatePreferredCurrency_UserNotFound() {
        String newCurrencyCode = "EUR";
        
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<?> response = currencyService.updatePreferredCurrency(999L, newCurrencyCode);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetPreferredCurrency() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        String preferredCurrency = currencyService.getPreferredCurrency(user.getId());
        
        assertEquals("USD", preferredCurrency);
        verify(userRepository).findById(user.getId());
    }

    @Test
    public void testGetPreferredCurrency_UserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        String preferredCurrency = currencyService.getPreferredCurrency(999L);
        
        assertNull(preferredCurrency);
    }
}
