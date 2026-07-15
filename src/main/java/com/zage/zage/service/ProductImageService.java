package com.zage.zage.service;

import com.zage.zage.dto.ProductImageDto;

import java.util.List;

public interface ProductImageService {
    ProductImageDto createProductImage(ProductImageDto dto);
    ProductImageDto getProductImageById(Integer id);
    List<ProductImageDto> getAllProductImages();
    ProductImageDto updateProductImage(Integer id, ProductImageDto dto);
    void deleteProductImage(Integer id);
}
