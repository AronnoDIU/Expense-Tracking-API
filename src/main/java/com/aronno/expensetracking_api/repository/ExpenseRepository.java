package com.aronno.expensetracking_api.repository;

import com.aronno.expensetracking_api.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    /**
     * Finds all expenses by category.
     *
     * @param category the category of the expense
     * @param page     the pagination information
     * @return a page of expenses with the specified category
     */
    Page<Expense> findByCategory(String category, Pageable page);

    /**
     * Finds all expenses between the specified start and end dates.
     *
     * @param startDate the start date of the expense
     * @param endDate   the end date of the expense
     * @param page      the pagination information
     * @return a page of expenses between the specified dates
     */
    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
}
