package com.codeid.eshopay.model.entity.fintech;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.entity.person.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts", schema = "fintech")
public class Account extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "fintech.accounts_account_id_seq", allocationSize = 1)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_no", length = 30, unique = true)
    private String accountNo;

    @Column(precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(length = 5)
    private String currency;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fint_code", referencedColumnName = "fint_code")
    private Fintech fintech;

    @OneToMany(mappedBy = "fromAccount")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "toAccount")
    private List<Transaction> incomingTransactions;
}
