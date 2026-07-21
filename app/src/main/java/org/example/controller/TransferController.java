package org.example.controller;

import org.example.dto.TransferRequest;
import org.example.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> transfer(
            @RequestBody TransferRequest request
    ) {

        try {
            transferService.transfer(
                    request.getFromAccountId(),
                    request.getToAccountId(),
                    request.getAmount()
            );
        } catch (IllegalArgumentException iex) {
            return ResponseEntity.badRequest().body(iex.getMessage());
        }
        return ResponseEntity.ok("Transfer successful");
    }
}
