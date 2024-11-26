import axios from "axios";
import AuthService from "./auth.service";
import API_BASE_URL from "./auth.config";

const getAllTransactions = (pagenumber, pageSize, searchKey) => {
    return axios.get(
        API_BASE_URL + "/transaction/getAll",
        {
            headers: AuthService.authHeader(),
            params: {
                pageNumber: pagenumber,
                pageSize: pageSize,
                searchKey: searchKey
            }
        }
    )
}

const getAllUsers = (pagenumber, pageSize, searchKey) => {
    return axios.get(
        API_BASE_URL + "/user/getAll",
        {
            headers: AuthService.authHeader(),
            params: {
                pageNumber: pagenumber,
                pageSize: pageSize,
                searchKey: searchKey
            }
        }
    )
}

const disableOrEnableUser = (userId) => {
    return axios.delete(
        API_BASE_URL + "/user/disable",
        {
            headers: AuthService.authHeader(),
            params: {
                userId: userId
            }
        }
    )
}

const getAllcategories = () => {
    return axios.get(
        API_BASE_URL + '/category/getAll', 
        {
            headers: AuthService.authHeader()
        }
    )
}

const updatecategory = (categoryId, categoryName, transactionTypeId) => {
    return axios.put(
        API_BASE_URL + '/category/update', 
        {
            categoryName: categoryName,
            transactionTypeId: transactionTypeId
        },
        {
            headers: AuthService.authHeader(),
            params: {
                categoryId: categoryId
            }
        }
    )
}

const disableOrEnableCategory = (categoryId) => {
    return axios.delete(
        API_BASE_URL + "/category/delete",
        {
            headers: AuthService.authHeader(),
            params: {
                categoryId: categoryId
            }
        }
    )
}

const addCategory = (categoryDetails) => {
  return axios.post(
    `${API_BASE_URL}/category/add`,
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


const AdminService = {
    getAllTransactions,
    getAllUsers,
    disableOrEnableUser,
    getAllcategories,
    updatecategory,
    disableOrEnableCategory,
    addCategory
}

export default AdminService;