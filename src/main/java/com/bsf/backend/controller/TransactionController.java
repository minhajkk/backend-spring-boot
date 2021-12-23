package com.bsf.backend.controller;

import com.bsf.backend.entity.Transaction;
import com.bsf.backend.entity.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/transactions")
@Validated
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/")
    public Transaction create(@Valid @RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @GetMapping
    public List<Transaction> list(){
        return (List<Transaction>) transactionRepository.findAll();
    }
}