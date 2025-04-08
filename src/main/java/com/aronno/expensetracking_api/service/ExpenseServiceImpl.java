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
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    @Autowired
    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository,
            UserService userService
    ) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            return expense.get();
        } else {
            throw new ResourceNotFoundException("Expense not found with id: " + id);
        }
    }

    @Override
    public Expense createExpense(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        Optional<Expense> existingExpense = expenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            Expense updatedExpense = existingExpense.get();
            updatedExpense.setName(expense.getName());
            updatedExpense.setDescription(expense.getDescription());
            updatedExpense.setAmount(expense.getAmount());
            updatedExpense.setCategory(expense.getCategory());
            updatedExpense.setDate(expense.getDate());
            return expenseRepository.save(updatedExpense);
        } else {
            throw new ResourceNotFoundException("Expense not found with id: " + id);
        }
    }

    @Override
    public void deleteExpense(Long id) {
        Optional<Expense> existingExpense = expenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            expenseRepository.delete(existingExpense.get());
        } else {
            throw new ResourceNotFoundException("Expense not found with id: " + id);
        }
    }

    @Override
    public List<Expense> getExpensesByCategory(String category, Pageable page) {
        return expenseRepository.findByCategory(category, page).toList();
    }

    @Override
    public List<Expense> getExpensesByDateRange(Date startDate, Date endDate, Pageable page) {

        if (startDate == null) {
            startDate = new Date(0); // Default to epoch time if no start date is provided
        }
        if (endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }
        
        return expenseRepository.findByDateBetween(startDate, endDate, page).toList(); 
    }
}
