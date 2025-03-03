package uz.pdp.ludito.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ludito.controller.converter.TransactionConverter;
import uz.pdp.ludito.controller.dto.TransactionCheckRequest;
import uz.pdp.ludito.controller.dto.TransactionPayRequest;
import uz.pdp.ludito.controller.dto.TransactionResponse;
import uz.pdp.ludito.entity.TransactionEntity;
import uz.pdp.ludito.service.TransactionService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/check")
    public TransactionResponse check(
            @RequestBody TransactionCheckRequest request
            ) {
        TransactionEntity transaction = transactionService.check(request);
        return TransactionConverter.fromEntity(transaction);
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/pay")
    public TransactionResponse pay(
            @RequestBody TransactionPayRequest request
    ) {
        TransactionEntity transaction = transactionService.pay(request.getTransactionId());
        return TransactionConverter.fromEntity(transaction);
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping
    public List<TransactionResponse> getAllTransactions(
             Principal principal
    ) {
        String agentUsername = principal.getName();
        List<TransactionEntity> transactions = transactionService.getAllTransactionsPerAgent(agentUsername);
        return TransactionConverter.fromEntity(transactions);
    }
}
