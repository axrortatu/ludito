package uz.pdp.ludito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.ludito.entity.AccountEntity;
import uz.pdp.ludito.entity.merchant.MerchantEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    AccountEntity getAccountEntityByMerchant(MerchantEntity merchantEntity);
}
