package com.fullStack.expenseTracker.services.impls;

import com.fullStack.expenseTracker.dtos.ApiResponseDto;
import com.fullStack.expenseTracker.services.CategoryService;
import com.fullStack.expenseTracker.services.TransactionTypeService;
import com.fullStack.expenseTracker.dto.reponses.ApiResponseDto;
import com.fullStack.expenseTracker.enums.ApiResponseStatus;
import com.fullStack.expenseTracker.exceptions.CategoryNotFoundException;
import com.fullStack.expenseTracker.exceptions.CategoryServiceLogicException;
import com.fullStack.expenseTracker.models.Category;
import com.fullStack.expenseTracker.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @Override
    public ResponseEntity<ApiResponseDto<?>> addCategory(Category category) throws CategoryServiceLogicException {
        try {
            Optional<Category> existingCategory = categoryRepository.findByName(category.getCategoryName());
            if (existingCategory.isPresent()) {
                throw new CategoryServiceLogicException("Category with the same name already exists!");
            }
            Category savedCategory = categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto<>(ApiResponseStatus.SUCCESS, HttpStatus.CREATED, savedCategory)
            );
        } catch (Exception e) {
            log.error("Failed to add category: " + e.getMessage());
            throw new CategoryServiceLogicException("Failed to add category: Try again later!");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> enableOrDisableCategory(int categoryId) throws CategoryServiceLogicException, CategoryNotFoundException {
        Category category = getCategoryById(categoryId);
        try {
            category.setEnabled(!category.isEnabled());
            categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseDto<>(ApiResponseStatus.SUCCESS, HttpStatus.OK, "Category has been updated successfully!")
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
    public ResponseEntity<ApiResponseDto<?>> getCategories() {
        return ResponseEntity.ok(
            new ApiResponseDto<>(ApiResponseStatus.SUCCESS, HttpStatus.OK, categoryRepository.findAll())
        );
    }
}
