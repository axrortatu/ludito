package uz.pdp.ludito.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.ludito.controller.dto.TransactionCheckRequest;
import uz.pdp.ludito.entity.ServiceEntity;
import uz.pdp.ludito.entity.TransactionEntity;
import uz.pdp.ludito.entity.agent.AgentEntity;
import uz.pdp.ludito.entity.enums.TransactionState;
import uz.pdp.ludito.exception.RecordNotFoundException;
import uz.pdp.ludito.factory.TransactionFactory;
import uz.pdp.ludito.repository.AgentRepository;
import uz.pdp.ludito.repository.ServiceRepository;
import uz.pdp.ludito.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ServiceRepository serviceRepository;
    private final AccountService accountService;
    private final TransactionFactory transactionFactory;
    private final AgentRepository agentRepository;

    public TransactionEntity check(TransactionCheckRequest request) {
        Optional<ServiceEntity> optionalServiceEntity
                = serviceRepository.findById(request.getServiceId());
        if (optionalServiceEntity.isEmpty()) {
            throw new RecordNotFoundException(String.format("Service with id %s not found",
                    request.getServiceId()));
        }
        ServiceEntity serviceEntity = optionalServiceEntity.get();
        TransactionEntity transactionEntity = transactionFactory.createTransaction(serviceEntity,
                request.getAccount(), BigDecimal.valueOf(request.getAmount()));
        transactionRepository.save(transactionEntity);

        try {
            log.warn("send request to check transaction to merchant ...");
            sendRequestToMerchant();
        }catch (Exception e) {
            log.error("check transaction to merchant failed", e);
            transactionEntity.setStatus(TransactionState.CHECKED_ERROR);
        }
        transactionEntity.setStatus(TransactionState.CHECKED);
        return transactionRepository.save(transactionEntity);
    }


    public TransactionEntity pay(long transactionId) {
        Optional<TransactionEntity> optionalTransactionEntity
                = transactionRepository.findById(transactionId);
        if (optionalTransactionEntity.isEmpty()) {
            throw new RecordNotFoundException(String.format("Transaction with id %s not found", transactionId));
        }
        TransactionEntity transactionEntity = optionalTransactionEntity.get();
        if (transactionEntity.getStatus() != TransactionState.CHECKED) {
            throw new IllegalStateException(String.format("Transaction with id %s is not checked", transactionId));
        }

        transactionEntity.setStatus(TransactionState.IN_PROCESS);
        transactionEntity.setUpdatedAt(Instant.now());
        accountService.withdraw(transactionEntity); //need to enhance merchant withdraw with validation

        try {
            log.warn("send request to pay transaction to merchant ...");
            sendRequestToMerchant();
        }catch (Exception e) {
            log.error("pay transaction to merchant failed", e);
            transactionEntity.setStatus(TransactionState.PAY_ERROR);
        }
        transactionEntity.setStatus(TransactionState.PAY_SUCCESS);
        return transactionRepository.save(transactionEntity);
    }

    public List<TransactionEntity> getAllTransactionsPerAgent(String agent) {
        Optional<AgentEntity> agentEntity = agentRepository.findByUsername(agent);
        if (agentEntity.isEmpty()) {
            throw new RecordNotFoundException(String.format("Agent %s not found", agent));
        }
        return transactionRepository.findAllByAgent(agent);
    }

    private void sendRequestToMerchant() {
        // call http request to original merchants such as ucell, beeline, paynet and etc
    }
}
