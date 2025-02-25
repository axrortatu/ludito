package uz.pdp.ludito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.ludito.entity.merchant.MerchantServiceEntity;

@Repository
public interface MerchantServiceRepository extends JpaRepository<MerchantServiceEntity, Integer> {
}
