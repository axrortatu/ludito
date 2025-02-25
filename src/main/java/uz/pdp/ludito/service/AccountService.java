package uz.pdp.ludito.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.ludito.entity.AccountEntity;
import uz.pdp.ludito.entity.ServiceEntity;
import uz.pdp.ludito.entity.TransactionEntity;
import uz.pdp.ludito.entity.merchant.MerchantEntity;
import uz.pdp.ludito.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void withdraw(TransactionEntity transactionEntity) {
        ServiceEntity serviceEntity = transactionEntity.getServiceEntity();
        MerchantEntity merchant = serviceEntity.getMerchantServiceEntity().getMerchant();
        AccountEntity accountEntity = accountRepository.getAccountEntityByMerchant(merchant);
        accountEntity.setBalance(accountEntity.getBalance().subtract(transactionEntity.getAmountToMerchant()));
        accountRepository.save(accountEntity);
    }
}
