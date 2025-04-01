package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.Expense;
import com.aronno.expensetracking_api.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            return expense.get();
        } else {
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }

    @Override
    public Expense createExpense(Expense expense) {
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
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }

    @Override
    public void deleteExpense(Long id) {
        Optional<Expense> existingExpense = expenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            expenseRepository.delete(existingExpense.get());
        } else {
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }
}
