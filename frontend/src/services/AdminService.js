import axios from 'axios';
import AuthService from './auth.service';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

/**
 * Axios POST request to /categories endpoint to add a new category.
 * @param {Object} categoryDetails - The details of the category to be added.
 * @returns {Promise} - The promise returned from the axios request.
 */
const addCategory = (categoryDetails) => {
  return axios.post(
    `${API_BASE_URL}/categories`,
    categoryDetails,
    {
      headers: AuthService.authHeader(),
    }
  )
  .then(response => response.data)
  .catch(error => {
    const message = error.response && error.response.data
      ? error.response.data.message
      : 'Failed to add category. Try again later.';
    throw new Error(message);
  });
};

export default {
  addCategory,
};
