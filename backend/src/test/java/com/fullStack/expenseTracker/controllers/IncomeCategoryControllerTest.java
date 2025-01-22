package com.fullStack.expenseTracker.controllers;

import com.fullStack.expenseTracker.services.CategoryService;
import com.fullStack.expenseTracker.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class IncomeCategoryControllerTest {

    @InjectMocks
    private IncomeCategoryController incomeCategoryController;

    @Mock
    private CategoryService categoryService;

    private Category mockCategory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCategory = new Category();
        mockCategory.setName("Salary");
        mockCategory.setType("Income");
    }

    @Test
    public void testCreateIncomeCategory() {
        // Arrange
        when(categoryService.createCategory(any(Category.class))).thenReturn(mockCategory);

        // Act
        Category createdCategory = incomeCategoryController.createIncomeCategory(mockCategory);

        // Assert
        assertEquals("Salary", createdCategory.getName());
        assertEquals("Income", createdCategory.getType());

        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryService, times(1)).createCategory(categoryCaptor.capture());
        assertEquals("Salary", categoryCaptor.getValue().getName());
        assertEquals("Income", categoryCaptor.getValue().getType());
    }

    @Test
    public void testCreateIncomeCategoryThrowsException() {
        // Arrange
        when(categoryService.createCategory(any(Category.class))).thenThrow(new RuntimeException("Category already exists"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            incomeCategoryController.createIncomeCategory(mockCategory);
        });

        assertEquals("Category already exists", exception.getMessage());
    }
}
