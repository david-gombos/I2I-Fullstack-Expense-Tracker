package com.fullStack.expenseTracker.services.impls;

import com.fullStack.expenseTracker.dtos.ApiResponseDto;
import com.fullStack.expenseTracker.dtos.ApiResponseStatus;
import com.fullStack.expenseTracker.entities.Category;
import com.fullStack.expenseTracker.entities.TransactionType;
import com.fullStack.expenseTracker.exceptions.CategoryServiceLogicException;
import com.fullStack.expenseTracker.exceptions.CategoryNotFoundException;
import com.fullStack.expenseTracker.repository.CategoryRepository;
import com.fullStack.expenseTracker.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> enableOrDisableCategory(int categoryId)
            throws CategoryServiceLogicException, CategoryNotFoundException {
        Category category = getCategoryById(categoryId);

        try {
            category.setEnabled(!category.isEnabled());
            categoryRepository.save(category);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponseDto<>(
                            ApiResponseStatus.SUCCESS, HttpStatus.OK, "Category has been updated successfully!"
                    )
            );
        } catch (Exception e) {
            log.error("Failed to enable/disable category: " + e.getMessage());
            throw new CategoryServiceLogicException("Failed to update category: Try again later!");
        }
    }

    @Override
    public boolean existsCategory(int id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public Category getCategoryById(int id) throws CategoryNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id" + id));
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponseDto<?>> addCategory(String categoryName, TransactionType transactionType)
            throws CategoryServiceLogicException {
        try {
            if (categoryRepository.existsByCategoryNameAndTransactionType(categoryName, transactionType)) {
                throw new CategoryServiceLogicException("Category with this name and transaction type already exists!");
            }

            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setTransactionType(transactionType);
            category.setEnabled(true);

            categoryRepository.save(category);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponseDto<>(
                            ApiResponseStatus.SUCCESS, HttpStatus.CREATED, "Category has been added successfully!"
                    )
            );

        } catch (Exception e) {
            log.error("Error adding new category: " + e.getMessage());
            throw new CategoryServiceLogicException("Failed to add new category: Try again later!");
        }
    }
}
