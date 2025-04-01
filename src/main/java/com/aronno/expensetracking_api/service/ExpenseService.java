package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);
}
