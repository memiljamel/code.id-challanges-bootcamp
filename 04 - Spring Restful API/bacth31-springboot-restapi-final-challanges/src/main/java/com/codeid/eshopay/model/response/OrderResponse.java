package com.codeid.eshopay.model.response;

import com.codeid.eshopay.model.enumeration.PaymentType;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Short orderId;

    private String orderNo;

    private LocalDate orderDate;

    private LocalDate requiredDate;

    private LocalDate shippedDate;

    private Float freight;

    private String shipName;

    private BigDecimal totalDiscount;

    private BigDecimal totalAmount;

    private PaymentType paymentType;

    private String cardNo;

    private String transacNo;

    private LocalDate transacDate;

    private String refNo;

    private List<OrderDetailResponse> details;

    private Integer userId;

    private Integer locationId;

    private Short shipperId;

    private String bankCode;

    private Integer employeeId;

    private Instant createdDate;

    private Instant modifiedDate;
}
