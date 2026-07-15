package com.zage.zage.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Integer id;
    private BigDecimal amount;
    private String method;
    private LocalDateTime paymentDate;
    private Integer ordersId;
    private Integer ordersCustomersId;
    private Integer ordersCustomersUsersId;
}
