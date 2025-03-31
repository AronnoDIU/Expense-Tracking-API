package com.aronno.expensetracking_api.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
    @GetMapping("/expenses")
    public String getExpenses() {
        return "List of Expenses";
    }
}
