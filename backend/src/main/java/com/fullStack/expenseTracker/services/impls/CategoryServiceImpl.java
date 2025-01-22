package com.fullStack.expenseTracker.services.impls;

import com.fullStack.expenseTracker.models.Category;
import com.fullStack.expenseTracker.repositories.CategoryRepository;
import com.fullStack.expenseTracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category createCategory(Category category) {
        // Validation to ensure the category name is unique and the type is set to 'Income'
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        if (!"Income".equalsIgnoreCase(category.getType())) {
            throw new IllegalArgumentException("Category type must be 'Income'");
        }

        // Check if category already exists
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category with this name already exists");
        }

        // Save the new category to the database
        return categoryRepository.save(category);
    }
}
