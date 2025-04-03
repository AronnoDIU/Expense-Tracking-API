package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    /**
     * Retrieves all expenses from the database.
     *
     * @param page the pagination information
     * @return a paginated list of all expenses
     */
    Page<Expense> getAllExpenses(Pageable page);

    /**
     * Retrieves an expense by its ID.
     *
     * @param id the ID of the expense to retrieve
     * @return the expense with the specified ID
     */
    Expense getExpenseById(Long id);

    /**
     * Creates a new expense in the database.
     *
     * @param expense the expense to create
     * @return the created expense
     */
    Expense createExpense(Expense expense);

    /**
     * Updates an existing expense in the database.
     *
     * @param id      the ID of the expense to update
     * @param expense the updated expense information
     * @return the updated expense
     */
    Expense updateExpense(Long id, Expense expense);

    /**
     * Deletes an expense by its ID.
     *
     * @param id the ID of the expense to delete
     */
    void deleteExpense(Long id);

    /**
     * Retrieves expenses by category.
     *
     * @param category the category of the expenses to retrieve
     * @param page     the pagination information
     * @return a paginated list of expenses in the specified category
     */
    List<Expense> getExpensesByCategory(String category, Pageable page);

    /**
     * Retrieves expenses within a specified date range.
     *
     * @param startDate the start date of the range
     * @param endDate   the end date of the range
     * @param page      the pagination information
     * @return a paginated list of expenses within the specified date range
     */
    List<Expense> getExpensesByDateRange(Date startDate, Date endDate, Pageable page);
}
