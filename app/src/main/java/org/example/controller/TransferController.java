package org.example.controller;

import org.example.dto.TransferRequest;
import org.example.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> transfer(TransferRequest request) {

        System.out.println("================== In Controller ====================");

        System.out.println(request.getFromAccountId());
        System.out.println(request.getToAccountId());
        System.out.println(request.getAmount());

        transferService.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount()
        );

        return ResponseEntity.ok("Transfer successful");
    }
}
