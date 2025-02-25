package uz.pdp.ludito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.ludito.entity.merchant.MerchantEntity;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, Integer> {
}
