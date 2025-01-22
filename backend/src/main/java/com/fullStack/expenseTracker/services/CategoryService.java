package com.fullStack.expenseTracker.services;

import com.fullStack.expenseTracker.models.Category;
import com.fullStack.expenseTracker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Creates and saves a new category to the database. This method includes validation
     * to ensure the category is unique and refers to the correct transaction type as 'Income'.
     *
     * @param categoryData The data for the category to be created.
     * @return The created Category object.
     * @throws IllegalArgumentException if the category is not valid or already exists.
     */
    public Category createCategory(Category categoryData) {
        // Validate input
        if (categoryData == null || categoryData.getName() == null || categoryData.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be empty");
        }

        // Check for existing category with the same name and type 'Income'
        Optional<Category> existingCategory = categoryRepository.findByNameAndType(categoryData.getName(), "Income");
        if (existingCategory.isPresent()) {
            throw new IllegalArgumentException("Category with this name already exists for Income type");
        }

        // Set the type to Income
        categoryData.setType("Income");

        // Save the new category
        return categoryRepository.save(categoryData);
    }
}
