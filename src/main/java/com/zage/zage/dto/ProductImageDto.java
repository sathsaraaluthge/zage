package com.zage.zage.dto;

import lombok.Data;

@Data
public class ProductImageDto {
    private Integer id;
    private String imageUrl;
    private String type;
    private Integer productsId;
    private Integer productsCategoriesId;
}
