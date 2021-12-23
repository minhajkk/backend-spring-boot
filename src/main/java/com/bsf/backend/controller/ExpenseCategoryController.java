package com.bsf.backend.controller;

import com.bsf.backend.entity.ExpenseCategory;
import com.bsf.backend.entity.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/categories")
@Validated
public class ExpenseCategoryController {

    @Autowired
    ExpenseCategoryRepository expenseCategoryRepository;

    @PostMapping("/")
    public ExpenseCategory create(@Valid @RequestBody ExpenseCategory expenseCategory){
        return expenseCategoryRepository.save(expenseCategory);
    }

    @GetMapping
    public List<ExpenseCategory> list(){
        return (List<ExpenseCategory>) expenseCategoryRepository.findAll();
    }
}
