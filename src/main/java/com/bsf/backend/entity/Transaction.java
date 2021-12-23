package com.bsf.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
public class Transaction {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "expense_category_id", nullable = false)
    ExpenseCategory expenseCategory;


    @Min(1)
    @Column(columnDefinition = "decimal(10,2) not null")
    BigDecimal amount;

    Date dateCreated;

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }
}