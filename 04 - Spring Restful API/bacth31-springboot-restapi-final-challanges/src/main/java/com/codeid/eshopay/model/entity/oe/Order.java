package com.codeid.eshopay.model.entity.oe;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.entity.fintech.Bank;
import com.codeid.eshopay.model.entity.hr.Employee;
import com.codeid.eshopay.model.entity.hr.Location;
import com.codeid.eshopay.model.entity.person.User;
import com.codeid.eshopay.model.enumeration.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "oe")
@EntityListeners({AuditingEntityListener.class})
public class Order extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "oe.orders_order_id_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Short orderId;

    @CreatedDate
    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "required_date")
    private LocalDate requiredDate;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @ManyToOne
    @JoinColumn(name = "ship_via", referencedColumnName = "shipper_id")
    private Shipper shipper;

    private Float freight;

    @Column(name = "ship_name", length = 40)
    private String shipName;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @Column(name = "order_no", length = 15)
    private String orderNo;

    @Column(name = "total_discount", precision = 5, scale = 2)
    private BigDecimal totalDiscount;

    @Column(name = "total_amount", precision = 8, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", length = 15)
    private PaymentType paymentType;

    @Column(name = "card_no", length = 25)
    private String cardNo;

    @Column(name = "transac_no", length = 25)
    private String transacNo;

    @CreatedDate
    @Column(name = "transac_date")
    private LocalDate transacDate;

    @Column(name = "ref_no", length = 25)
    private String refNo;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bank_code", referencedColumnName = "bank_code")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    @PrePersist
    private void setOrderNo() {
        this.orderNo = "ORD" + String.format("%012d", orderId);
    }
}
