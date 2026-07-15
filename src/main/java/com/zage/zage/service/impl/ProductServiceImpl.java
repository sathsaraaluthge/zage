package com.zage.zage.service.impl;

import com.zage.zage.dto.ProductDto;
import com.zage.zage.repository.ProductRepository;
import com.zage.zage.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public ProductDto createProduct(ProductDto dto) {
        return productRepository.registerProduct(dto);
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductDto dto) {
        return productRepository.updateProduct(id, dto);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public ProductDto getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(
                () -> new com.zage.zage.exception.ResourceNotFoundException("Product not found")
        );
    }

    @Override
    public List<ProductDto> searchProducts(String name, String status, Integer categoryId,
                                           String productType, java.math.BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.searchProducts(name, status, categoryId, productType, minPrice, maxPrice);
    }
}
