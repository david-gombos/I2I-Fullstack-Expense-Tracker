package com.fullStack.expenseTracker.controllers;

import com.fullStack.expenseTracker.dto.ApiResponseDto;
import com.fullStack.expenseTracker.dto.ApiResponseStatus;
import com.fullStack.expenseTracker.models.Category;
import com.fullStack.expenseTracker.services.CategoryService;
import com.fullStack.expenseTracker.services.exceptions.CategoryServiceLogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mywallet/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponseDto<?>> addCategory(@Validated @RequestBody Category category) {
        try {
            Category newCategory = categoryService.addCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS, HttpStatus.CREATED, newCategory));
        } catch (CategoryServiceLogicException e) {
            log.error("Failed to add category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDto<>(ApiResponseStatus.FAILED, HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponseDto<?>> disableOrEnableCategory(@RequestParam("categoryId") int categoryId) 
            throws CategoryServiceLogicException, CategoryNotFoundException {
        return categoryService.enableOrDisableCategory(categoryId);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponseDto<?>> getAllCategories() {
        return categoryService.getCategories();
    }
}
