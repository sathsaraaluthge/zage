package com.zage.zage.dto;

import com.zage.zage.enums.ProductType;
import com.zage.zage.enums.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private ProductType productType;
    private Boolean isCustomizable;
    private BigDecimal sellingPrice;
    private BigDecimal rentalPrice;
    private Status status;
    private Integer categoryId;
    private LocalDateTime createdAt;


}
