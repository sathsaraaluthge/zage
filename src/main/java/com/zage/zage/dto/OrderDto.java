package com.zage.zage.dto;

import com.zage.zage.enums.OrderType;
import com.zage.zage.enums.Status;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderDto {
    private Integer id;
    private OrderType orderType;
    private BigDecimal totalAmount;
    private Status status;
    private Integer customersId;
    private Integer customersUsersId;
}
