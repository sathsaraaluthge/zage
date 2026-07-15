package com.zage.zage.dto;

import lombok.Data;

@Data
public class PackageItemDto {
    private Integer id;
    private Integer quantity;
    private Integer weddingPackagesId;
    private Integer productsId;
    private Integer productsCategoriesId;
}
