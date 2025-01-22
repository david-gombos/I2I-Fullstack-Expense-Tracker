package com.fullStack.expenseTracker.controllers;

import com.fullStack.expenseTracker.models.Category;
import com.fullStack.expenseTracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controller to manage income categories
@RestController
@RequestMapping("/api/income-categories")
public class IncomeCategoryController {

    @Autowired
    private CategoryService categoryService;

    // Method to create a new income category
    @PostMapping
    public ResponseEntity<String> createIncomeCategory(@RequestBody Category category) {
        try {
            // Validate input
            if (category == null || category.getName() == null || category.getName().trim().isEmpty()) {
                return new ResponseEntity<>("Category data is invalid.", HttpStatus.BAD_REQUEST);
            }

            // Set the type as Income if not already set
            if (category.getType() == null) {
                category.setType("Income");
            }

            // Call CategoryService to save the category
            categoryService.createCategory(category);
            return new ResponseEntity<>("Income category created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error and return a server error response
            // Ideally, use a logger here
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while creating the income category.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
