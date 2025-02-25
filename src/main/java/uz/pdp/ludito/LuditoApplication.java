package uz.pdp.ludito;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.ludito.entity.AccountEntity;
import uz.pdp.ludito.entity.ServiceEntity;
import uz.pdp.ludito.entity.agent.RoleEntity;
import uz.pdp.ludito.entity.enums.UserRole;
import uz.pdp.ludito.entity.merchant.MerchantEntity;
import uz.pdp.ludito.entity.merchant.MerchantServiceEntity;
import uz.pdp.ludito.repository.*;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
public class LuditoApplication implements CommandLineRunner {
    private final MerchantRepository merchantRepository;
    private final MerchantServiceRepository merchantServiceRepository;
    private final ServiceRepository serviceRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;


    public static void main(String[] args) {
        SpringApplication.run(LuditoApplication.class, args);
    }


    // inserted just mock data
    @Override
    public void run(String... args) {
        MerchantEntity merchant = new MerchantEntity();
        merchant.setName("PAYNET");
        merchant = merchantRepository.save(merchant);


        MerchantServiceEntity merchantService = new MerchantServiceEntity();
        merchantService.setMerchant(merchant);
        merchantService.setName("PAYNET_UCELL");
        merchantService = merchantServiceRepository.save(merchantService);

        ServiceEntity service = new ServiceEntity();
        service.setName("UCELL");
        service.setMerchantServiceEntity(merchantService);

        serviceRepository.save(service);
        roleRepository.save(new RoleEntity(UserRole.AGENT));

        AccountEntity account = new AccountEntity();
        account.setMerchant(merchant);
        account.setBalance(BigDecimal.valueOf(10000000));
        accountRepository.save(account);
    }
}
