import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import CurrencySelector from '../components/CurrencySelector';
import { fetchCurrencies } from '../api/getCurrencies';

jest.mock('../api/getCurrencies');

describe('CurrencySelector Component', () => {
    beforeEach(() => {
        // Clear any previous mocks before each test
        jest.clearAllMocks();
    });

    test('testGetCurrencies: It should correctly fetch and return currency data', async () => {
        const mockCurrencies = ['USD', 'EUR', 'GBP'];
        fetchCurrencies.mockResolvedValueOnce(mockCurrencies); // Mocking the API response

        const currencies = await fetchCurrencies();
        expect(currencies).toEqual(mockCurrencies);
        expect(fetchCurrencies).toHaveBeenCalledTimes(1); // Ensure the function was called once
    });

    test('testRenderCurrencyDropdown: It should render dropdown correctly with fetched currencies', async () => {
        const mockCurrencies = ['USD', 'EUR', 'GBP'];
        fetchCurrencies.mockResolvedValueOnce(mockCurrencies); // Mocking the API response

        render(<CurrencySelector />); // Render the CurrencySelector component
        
        await waitFor(() => {
            // Check if dropdown is rendered with the correct options
            expect(screen.getByRole('combobox')).toBeInTheDocument(); // Ensure dropdown exists
            mockCurrencies.forEach(currency => {
                expect(screen.getByText(currency)).toBeInTheDocument(); // Each currency option should be present
            });
        });
    });
});
