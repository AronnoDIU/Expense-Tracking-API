package com.aronno.expensetracking_api.controller;

import com.aronno.expensetracking_api.entity.Expense;
import com.aronno.expensetracking_api.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @GetMapping("/expenses")
    public List<Expense> getExpenses() {
        expenseService.getAllExpenses();
        return expenseService.getAllExpenses();
    }
}
