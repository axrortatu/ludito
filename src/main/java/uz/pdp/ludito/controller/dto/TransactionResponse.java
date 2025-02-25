package uz.pdp.ludito.controller.dto;

import lombok.Builder;
import lombok.Data;
import uz.pdp.ludito.entity.enums.TransactionState;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionResponse {
    private long id;

    private ServiceResponse service;
    private MerchantResponse merchant;

    private BigDecimal amount;
    private BigDecimal amountToMerchant;

    private String account;
    private TransactionState status;
    private String agent;
}
