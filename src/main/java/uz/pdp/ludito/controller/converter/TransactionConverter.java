package uz.pdp.ludito.controller.converter;

import uz.pdp.ludito.controller.dto.MerchantResponse;
import uz.pdp.ludito.controller.dto.ServiceResponse;
import uz.pdp.ludito.controller.dto.TransactionResponse;
import uz.pdp.ludito.entity.ServiceEntity;
import uz.pdp.ludito.entity.TransactionEntity;
import uz.pdp.ludito.entity.merchant.MerchantEntity;

import java.util.List;

public class TransactionConverter {

    public static List<TransactionResponse> fromEntity(List<TransactionEntity> entities) {
        return entities.stream().map(TransactionConverter::fromEntity).toList();
    }

    public static TransactionResponse fromEntity(TransactionEntity transactionEntity) {
        return TransactionResponse.builder()
                .id(transactionEntity.getId())
                .account(transactionEntity.getAccount())
                .amount(transactionEntity.getAmount())
                .amountToMerchant(transactionEntity.getAmountToMerchant())
                .service(fromEntity(transactionEntity.getServiceEntity()))
                .merchant(fromEntity(transactionEntity.getServiceEntity()
                        .getMerchantServiceEntity().getMerchant()))
                .agent(transactionEntity.getAgent())
                .status(transactionEntity.getStatus())
                .build();
    }

    private static ServiceResponse fromEntity(ServiceEntity serviceEntity) {
        return ServiceResponse.builder()
                .id(serviceEntity.getId())
                .name(serviceEntity.getName())
                .build();
    }

    private static MerchantResponse fromEntity(MerchantEntity merchantEntity) {
        return MerchantResponse.builder()
                .id(merchantEntity.getId())
                .name(merchantEntity.getName())
                .build();
    }
}
