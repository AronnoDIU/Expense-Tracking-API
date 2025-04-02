package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    Page<Expense> getAllExpenses(Pageable page);

    Expense getExpenseById(Long id);

    Expense createExpense(Expense expense);

    Expense updateExpense(Long id, Expense expense);

    void deleteExpense(Long id);

    List<Expense> getExpensesByCategory(String category, Pageable page);

    List<Expense> getExpensesByDateRange(Date startDate, Date endDate, Pageable page);
}
