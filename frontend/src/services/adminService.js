import axios from 'axios';
import AuthService from './auth.service';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

const addCategory = (categoryName, transactionTypeId) => {
    return axios.post(
        `${API_BASE_URL}/category/new`,
        {
            categoryName: categoryName,
            transactionTypeId: transactionTypeId
        },
        {
            headers: AuthService.authHeader()
        }
    ).then((response) => {
        if (response.data.status === "SUCCESS") {
            return Promise.resolve(response.data.response);
        }
        return Promise.reject(response.data.response);
    }).catch((error) => {
        if (error.response) {
            return Promise.reject(error.response.data.response);
        }
        return Promise.reject("Failed to axios.post method to make an authenticated request to our backend.

