package org.example.repository;

import org.example.model.Account;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {

    private final ConcurrentHashMap<Long, Account> accounts = new ConcurrentHashMap<>();

    public void save(Account account) {
        accounts.put(account.getId(), account);
    }

    public Account findById(long id) {
        return accounts.get(id);
    }

    public ConcurrentHashMap<Long, Account> getAllAccounts() {
        return accounts;
    }
}
