package com.zage.zage.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductVariantDto {
    private Integer id;
    private String sku;
    private BigDecimal price;
    private BigDecimal stockQty;
    private Integer productsId;
    private Integer productsCategoriesId;
    private Integer colorsId;
    private Integer materialsId;
    private Integer sizesId;
}
