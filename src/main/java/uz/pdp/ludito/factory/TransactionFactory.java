package uz.pdp.ludito.factory;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.ludito.entity.ServiceEntity;
import uz.pdp.ludito.entity.TransactionEntity;
import uz.pdp.ludito.entity.enums.TransactionState;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Component
public class TransactionFactory {

    public TransactionEntity createTransaction(ServiceEntity serviceEntity,
                                               String account,
                                               BigDecimal amount) {
        Instant now = Instant.now();
        return TransactionEntity
                .builder()
                .status(TransactionState.CREATED)
                .account(account)
                .serviceEntity(serviceEntity)
                .agent(getCurrentAgent())
                .amount(amount)
                .amountToMerchant(calculateAmountToMerchant(
                        amount,
                        serviceEntity.getMerchantServiceEntity().getCommission()
                ))
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    private static BigDecimal calculateAmountToMerchant(BigDecimal amount, int commission) {
        BigDecimal commissionRate = BigDecimal.valueOf(commission).divide(
                BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return amount.multiply(BigDecimal.ONE.subtract(commissionRate));
    }


    private String getCurrentAgent() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
