package org.example.service;

import org.example.model.Account;
import org.example.model.Transaction;
import org.example.repository.AccountRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransferService {

    private final AccountRepository repository;

    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

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

            transactions.add(
                    new Transaction(
                            first.getId(),
                            second.getId(),
                            amount,
                            Instant.now()
                    )
            );
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
