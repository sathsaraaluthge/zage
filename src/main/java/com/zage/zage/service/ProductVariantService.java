package com.zage.zage.service;

import com.zage.zage.dto.ProductVariantDto;

import java.util.List;

public interface ProductVariantService {
    ProductVariantDto createProductVariant(ProductVariantDto dto);
    ProductVariantDto getProductVariantById(Integer id);
    List<ProductVariantDto> getAllProductVariants();
    ProductVariantDto updateProductVariant(Integer id, ProductVariantDto dto);
    void deleteProductVariant(Integer id);
}
