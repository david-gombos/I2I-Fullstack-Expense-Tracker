package com.fullStack.expenseTracker.repository;

import com.fullStack.expenseTracker.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    /**
     * Checks if a category exists by name and transaction type.
     * 
     * @param categoryName the name of the category.
     * @param transactionType the transaction type of the category.
     * @return true if the category exists, false otherwise.
     */
    boolean existsByCategoryNameAndTransactionType(String categoryName, TransactionType transactionType);

    /**
     * Save a new category entity in the database.
     *
     * @param category the category entity to save.
     * @return the saved category entity.
     */
    default Category saveCategory(Category category) {
        try {
            return save(category);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save category: " + e.getMessage(), e);
        }
    }
}
