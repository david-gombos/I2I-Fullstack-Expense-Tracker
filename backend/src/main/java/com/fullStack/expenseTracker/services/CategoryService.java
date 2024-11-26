package com.fullStack.expenseTracker.services;

import com.fullStack.expenseTracker.dto.ApiResponseDto;
import com.fullStack.expenseTracker.enums.ApiResponseStatus;
import com.fullStack.expenseTracker.exceptions.CategoryServiceLogicException;
import com.fullStack.expenseTracker.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    ResponseEntity<ApiResponseDto<?>> getCategories();

    boolean existsCategory(int id);

    Category getCategoryById(int id) throws CategoryNotFoundException;

    ResponseEntity<ApiResponseDto<?>> enableOrDisableCategory(int categoryId) throws CategoryServiceLogicException, CategoryNotFoundException;

    Category addCategory(Category category) throws CategoryServiceLogicException;
}
