package org.example.controller;

import org.example.model.Transaction;
import org.example.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransferService transferService;

    public TransactionController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> ListTransactions() {

        List<Transaction> transactions = transferService.getTransactions();
        return ResponseEntity.ok(transactions);

    }
}
