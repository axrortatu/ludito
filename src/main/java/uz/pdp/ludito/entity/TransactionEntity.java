package uz.pdp.ludito.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import uz.pdp.ludito.entity.enums.TransactionState;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "lo_transaction")
public class TransactionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ServiceEntity serviceEntity;

    private BigDecimal amount;
    private BigDecimal amountToMerchant;

    private String account;
    private TransactionState status;
    private String agent;
    private Instant createdAt;
    private Instant updatedAt;
}
