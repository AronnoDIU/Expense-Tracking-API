package com.aronno.expensetracking_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_name")
    @NotBlank(message = "Expense name cannot be blank")
    @Size(min = 3, message = "Expense name must be at least 3 characters")
    private String name;

    private String description;

    @Column(name = "expense_amount", precision = 10, scale = 2)
    @NotNull(message = "Expense amount cannot be null")
    private BigDecimal amount;

    @NotBlank(message = "Expense category cannot be blank")
    private String category;

    @NotNull(message = "Expense date cannot be null")
    private Date date;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
