package com.zage.zage.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WeddingPackageDto {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String description;
}
