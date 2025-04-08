package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.Expense;
import com.aronno.expensetracking_api.exceptions.ResourceNotFoundException;
import com.aronno.expensetracking_api.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        Long userId = userService.getLoggedInUser().getId();
        return expenseRepository.findByUserId(userId, page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Long userId = userService.getLoggedInUser().getId();
        return expenseRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
    }

    @Override
    public Expense createExpense(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);

        updateFieldIfNotNull(expense.getName(), existingExpense::setName);
        updateFieldIfNotNull(expense.getDescription(), existingExpense::setDescription);
        updateFieldIfNotNull(expense.getAmount(), existingExpense::setAmount);
        updateFieldIfNotNull(expense.getCategory(), existingExpense::setCategory);
        updateFieldIfNotNull(expense.getDate(), existingExpense::setDate);

        return expenseRepository.save(existingExpense);
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public List<Expense> getExpensesByCategory(String category, Pageable page) {
        Long userId = userService.getLoggedInUser().getId();
        return expenseRepository.findByCategoryAndUserId(category, userId, page).toList();
    }

    @Override
    public List<Expense> getExpensesByDateRange(Date startDate, Date endDate, Pageable page) {
        Long userId = userService.getLoggedInUser().getId();

        if (startDate == null) {
            startDate = new Date(0); // Default to epoch time if no start date is provided
        }
        if (endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }

        return expenseRepository.findByDateBetweenAndUserId(startDate, endDate, userId, page).toList();
    }

    /**
     * Helper method to update a field if the new value is not null.
     *
     * @param newValue the new value to set
     * @param setter   the setter method to call
     * @param <T>      the type of the field
     */
    private <T> void updateFieldIfNotNull(T newValue, java.util.function.Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }
}
