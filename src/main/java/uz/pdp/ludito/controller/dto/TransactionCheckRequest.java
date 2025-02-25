package uz.pdp.ludito.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionCheckRequest {
    private int serviceId;
    private double amount;
    private String account;
}
