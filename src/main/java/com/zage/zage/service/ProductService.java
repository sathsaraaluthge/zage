package com.zage.zage.service;

import com.zage.zage.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto dto);
    ProductDto updateProduct(Integer id, ProductDto dto);
    void deleteProduct(Integer id);
    ProductDto getProductById(Integer id);
    List<ProductDto> searchProducts(String name, String status, Integer categoryId,
                                    String productType, BigDecimal minPrice, BigDecimal maxPrice);
}
