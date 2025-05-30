package com.codeid.eshopay.model.entity.fintech;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.enumeration.TransactionStatus;
import com.codeid.eshopay.model.enumeration.TransactionType;
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
@Table(name = "transactions", schema = "fintech")
public class Transaction extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trac_seq")
    @SequenceGenerator(name = "trac_seq", sequenceName = "fintech.transactions_trac_id_seq", allocationSize = 1)
    @Column(name = "trac_id")
    private Long tracId;

    @Column(name = "trac_no", length = 25, unique = true)
    private String tracNo;

    @ManyToOne
    @JoinColumn(name = "from_account", referencedColumnName = "account_no")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account", referencedColumnName = "account_no")
    private Account toAccount;

    @OneToMany(mappedBy = "tracNoRef")
    private List<Transaction> tracNoRefBy;

    @ManyToOne
    @JoinColumn(name = "trac_noref", referencedColumnName = "trac_no")
    private Transaction tracNoRef;

    @Column(precision = 15, scale = 2)
    private BigDecimal debet;

    @Column(precision = 15, scale = 2)
    private BigDecimal credit;

    @Enumerated(EnumType.STRING)
    @Column(name = "trac_type", length = 15)
    private TransactionType tracType;

    @Column(length = 35)
    private String description;

    @CreatedDate
    @Column(name = "trac_date")
    private Instant tracDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TransactionStatus status;
}
