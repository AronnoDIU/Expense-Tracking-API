package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense createExpense(Expense expense);

    Expense updateExpense(Long id, Expense expense);

    void deleteExpense(Long id);
}
