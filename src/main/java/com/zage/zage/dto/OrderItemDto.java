package com.zage.zage.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Integer id;
    private Integer quantity;
    private BigDecimal price;
    private Integer ordersId;
    private Integer ordersCustomersId;
    private Integer ordersCustomersUsersId;
    private Integer productsId;
    private Integer productsCategoriesId;
    private Integer productVariantsId;
    private Integer productVariantsProductsId;
    private Integer productVariantsProductsCategoriesId;
    private Integer productVariantsColorsId;
    private Integer productVariantsMaterialsId;
    private Integer productVariantsSizesId;
}
