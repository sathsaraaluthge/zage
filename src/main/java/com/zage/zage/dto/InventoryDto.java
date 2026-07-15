package com.zage.zage.dto;

import lombok.Data;

@Data
public class InventoryDto {
    private Integer id;
    private Integer quantity;
    private String location;
    private Integer productVariantsId;
    private Integer productVariantsProductsId;
    private Integer productVariantsProductsCategoriesId;
    private Integer productVariantsColorsId;
    private Integer productVariantsMaterialsId;
    private Integer productVariantsSizesId;
}
