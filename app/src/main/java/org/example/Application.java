package org.example;

import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner loadData(AccountRepository repository) {
        return args -> {

            repository.save(
                    new Account(1, new BigDecimal("100"))
            );

            repository.save(
                    new Account(2, new BigDecimal("50"))
            );

//            System.out.println("Sample accounts loaded");
        };
    }
}
