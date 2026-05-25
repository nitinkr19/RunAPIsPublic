package org.example.service;

import org.example.model.Account;
import org.example.repository.AccountRepository;

import java.math.BigDecimal;

public class TransferService {

    private final AccountRepository repository;

    public TransferService(AccountRepository repository) {
        this.repository = repository;
    }

    public void transfer(long fromId, long toId, BigDecimal amount) {

        if(fromId == toId) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Account from = repository.findById(fromId);
        Account to = repository.findById(toId);

        if (from == null || to == null) {
            throw new IllegalArgumentException("Account not found");
        }

        // Deadlock prevention using lock ordering
        Account first = from.getId() < to.getId() ? from : to;
        Account second = from.getId() > to.getId() ? from : to;

        synchronized (first) {
            if (first.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient funds");
            }

            first.withdraw(amount);
            second.deposit(amount);
        }
    }
}
