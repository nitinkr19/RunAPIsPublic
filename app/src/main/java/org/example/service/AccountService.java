package org.example.service;

import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> getAccounts() {
        ConcurrentHashMap<Long, Account> accounts = repository.getAllAccounts();
        return new ArrayList<>(accounts.values());
    }
}
