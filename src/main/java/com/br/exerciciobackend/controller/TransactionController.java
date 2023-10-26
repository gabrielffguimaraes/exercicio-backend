package com.br.exerciciobackend.controller;

import com.br.exerciciobackend.dto.TransactionDto;
import com.br.exerciciobackend.model.Transaction;
import com.br.exerciciobackend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTrasaction(@RequestBody TransactionDto transactionDto) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(newTransaction,HttpStatus.CREATED);
    }
}
