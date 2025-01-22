// frontend/src/api/getCurrencies.js

/**
 * fetchCurrencies - This function will be responsible for making the API call
 * to retrieve the list of available currencies, handling any errors,
 * and returning the currency data.
 * 
 * @returns {Promise<Array>} - A promise that resolves to an array of currency data.
 * @throws {Error} - Throws an error if the API call fails or returns an unexpected response.
 */
export const fetchCurrencies = async () => {
    const API_URL = 'https://api.exchangeratesapi.io/latest'; // Example API endpoint for fetching currencies

    try {
        const response = await fetch(API_URL);

        // Validate response
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();

        // Checking if data contains the expected structure
        if (!data || !data.rates) {
            throw new Error('Invalid data structure returned from API');
        }

        // Convert rates object to an array of currencies
        const currencies = Object.keys(data.rates).map(currency => ({
            code: currency,
            rate: data.rates[currency]
        }));

        return currencies;
    } catch (error) {
        console.error('Fetch currencies failed:', error);
        throw new Error('Failed to fetch currencies. Please try again later.');
    }
};
