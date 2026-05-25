package org.example.repository;

import org.example.model.Account;

import java.util.concurrent.ConcurrentHashMap;

public class AccountRepository {

    private final ConcurrentHashMap<Long, Account> accounts = new ConcurrentHashMap<>();

    public void save(Account account) {
        accounts.put(account.getId(), account);
    }

    public Account findById(long id) {
        return accounts.get(id);
    }
}
