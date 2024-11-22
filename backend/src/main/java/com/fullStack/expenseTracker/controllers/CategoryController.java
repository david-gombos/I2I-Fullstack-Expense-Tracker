package com.fullStack.expenseTracker.controllers;

import com.fullStack.expenseTracker.dto.ApiResponseDto;
import com.fullStack.expenseTracker.dto.ApiResponseStatus;
import com.fullStack.expenseTracker.exceptions.CategoryServiceLogicException;
import com.fullStack.expenseTracker.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mywallet/category")
@CrossOrigin(origins = "*")
@Slf4j
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponseDto<?>> addCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        try {
            return categoryService.addCategory(categoryRequestDto);
        } catch (CategoryServiceLogicException e) {
            log.error("Failed to add new category: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDto<>(ApiResponseStatus.FAILED, HttpStatus.BAD_REQUEST, "Failed to add new category: " + e.getMessage()));
        } catch (Exception e) {
            log.error("Unexpected error occurred while adding new category: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(ApiResponseStatus.FAILED, HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred. Please try again later."));
        }
    }
}
