import React, { useEffect, useState } from 'react';
import { fetchCurrencies } from '../api/getCurrencies';
import './CurrencySelector.css';

const CurrencySelector = () => {
    const [currencies, setCurrencies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fetch currencies from the API
    const getCurrencies = async () => {
        try {
            const currencyData = await fetchCurrencies();
            setCurrencies(currencyData);
        } catch (err) {
            setError('Failed to fetch currencies. Please try again later.');
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    // Render dropdown for currency selection
    const renderCurrencyDropdown = () => {
        if (loading) {
            return <option>Loading...</option>;
        }

        if (error) {
            return <option>{error}</option>;
        }

        return currencies.map(currency => (
            <option key={currency.code} value={currency.code}>
                {currency.name} ({currency.code})
            </option>
        ));
    };

    useEffect(() => {
        getCurrencies();
    }, []);

    return (
        <select className="currency-dropdown">
            {renderCurrencyDropdown()}
        </select>
    );
};

export default CurrencySelector;
