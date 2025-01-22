package com.fullStack.expenseTracker.services;

import com.fullStack.expenseTracker.models.Category;
import com.fullStack.expenseTracker.services.impls.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository; // Assuming you're using a repository interface

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        // Arrange
        Category category = new Category();
        category.setName("Salary");
        category.setType("Income");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        
        // Act
        Category createdCategory = categoryService.createCategory(category);

        // Assert
        assertNotNull(createdCategory);
        assertEquals("Salary", createdCategory.getName());
        assertEquals("Income", createdCategory.getType());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    public void testCreateCategory_WithValidationError() {
        // Arrange
        Category category = new Category();
        category.setName(""); // Invalid name
        category.setType("Income");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.createCategory(category);
        });

        String expectedMessage = "Category name cannot be empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(categoryRepository, never()).save(any(Category.class));
    }
}
