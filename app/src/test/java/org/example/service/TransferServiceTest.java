package org.example.service;

import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TransferServiceTest {

    @Test
    void shouldTransferMoneySuccessfully() {

        AccountRepository repository = new AccountRepository();

        Account a = new Account(1,  new BigDecimal(100));
        Account b = new Account(2, new BigDecimal(50));

        repository.save(a);
        repository.save(b);

        TransferService service = new TransferService(repository);

        service.transfer(1, 2, new BigDecimal(20));

        assertEquals(new BigDecimal(80), a.getBalance());
        assertEquals(new BigDecimal(70), b.getBalance());
    }

    @Test
    void shouldHandleConcurrentTransfers() throws InterruptedException {

        AccountRepository repository = new AccountRepository();

        Account a = new Account(1, new BigDecimal(1000));
        Account b = new Account(2, new BigDecimal(1000));

        repository.save(a);
        repository.save(b);

        TransferService service = new TransferService(repository);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                service.transfer(1,2, new BigDecimal(1));
                service.transfer(2,1, new BigDecimal(1));
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        BigDecimal total = a.getBalance().add(b.getBalance());
        assertEquals(new BigDecimal(2000), total);

    }

}