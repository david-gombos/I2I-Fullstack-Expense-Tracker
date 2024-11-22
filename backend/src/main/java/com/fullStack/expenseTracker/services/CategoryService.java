package com.fullStack.expenseTracker.services;

import com.fullStack.expenseTracker.dtos.ApiResponseDto;
import com.fullStack.expenseTracker.dtos.ApiResponseStatus;
import com.fullStack.expenseTracker.entities.Category;
import com.fullStack.expenseTracker.exceptions.CategoryServiceLogicException;
import com.fullStack.expenseTracker.exceptions.CategoryNotFoundException;
import org.springframework.http.ResponseEntity;

/**
 * Service interface for handling Category related operations.
 */
public interface CategoryService {

    /**
     * Fetches all categories.
     *
     * @return a list of all categories wrapped in an ApiResponseDto.
     */
    ResponseEntity<ApiResponseDto<?>> getAllCategories();

    /**
     * Checks if a category exists by its ID.
     *
     * @param categoryId the ID of the category to check.
     * @return true if the category exists, false otherwise.
     */
    boolean existsCategory(int categoryId);

    /**
     * Fetches a category by its ID.
     *
     * @param id the ID of the category.
     * @return the fetched category entity.
     * @throws CategoryNotFoundException if the category is not found.
     */
    Category getCategoryById(int id) throws CategoryNotFoundException;

    /**
     * Enables or disables a category.
     *
     * @param categoryId the ID of the category to enable or disable.
     * @return a response entity containing the status of the operation.
     * @throws CategoryServiceLogicException if any logic error occurs.
     * @throws CategoryNotFoundException if the category is not found.
     */
    ResponseEntity<ApiResponseDto<?>> enableOrDisableCategory(int categoryId) 
            throws CategoryServiceLogicException, CategoryNotFoundException;

    /**
     * Adds a new category.
     *
     * @param categoryName the name of the category.
     * @param transactionType the transaction type of the category.
     * @return a response entity containing the status of the operation.
     * @throws CategoryServiceLogicException if any validation error occurs.
     */
    ResponseEntity<ApiResponseDto<?>> addCategory(String categoryName, String transactionType) 
            throws CategoryServiceLogicException;
}
