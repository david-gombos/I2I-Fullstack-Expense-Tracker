package com.fullStack.expenseTracker.controllers;

import com.fullStack.expenseTracker.dto.requests.CurrencyRequestDto;
import com.fullStack.expenseTracker.services.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CurrencyControllerTest {

    @InjectMocks
    private CurrencyController currencyController;

    @Mock
    private CurrencyService currencyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdatePreferredCurrency_ValidCurrencyCode() {
        CurrencyRequestDto request = new CurrencyRequestDto();
        request.setCurrencyCode("USD");

        when(currencyService.updatePreferredCurrency(any(CurrencyRequestDto.class))).thenReturn(true);

        ResponseEntity<String> response = currencyController.updatePreferredCurrency(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Currency updated successfully.", response.getBody());

        ArgumentCaptor<CurrencyRequestDto> currencyRequestCaptor = ArgumentCaptor.forClass(CurrencyRequestDto.class);
        verify(currencyService, times(1)).updatePreferredCurrency(currencyRequestCaptor.capture());
        assertEquals("USD", currencyRequestCaptor.getValue().getCurrencyCode());
    }

    @Test
    public void testUpdatePreferredCurrency_InvalidCurrencyCode() {
        CurrencyRequestDto request = new CurrencyRequestDto();
        request.setCurrencyCode("INVALID");

        when(currencyService.updatePreferredCurrency(any(CurrencyRequestDto.class))).thenReturn(false);

        ResponseEntity<String> response = currencyController.updatePreferredCurrency(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid currency code.", response.getBody());
    }

    @Test
    public void testGetPreferredCurrency_Success() {
        String expectedCurrency = "USD";
        when(currencyService.getPreferredCurrency()).thenReturn(expectedCurrency);

        ResponseEntity<String> response = currencyController.getPreferredCurrency();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCurrency, response.getBody());
    }

    @Test
    public void testGetPreferredCurrency_NotFound() {
        when(currencyService.getPreferredCurrency()).thenReturn(null);

        ResponseEntity<String> response = currencyController.getPreferredCurrency();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Preferred currency not found.", response.getBody());
    }
}
