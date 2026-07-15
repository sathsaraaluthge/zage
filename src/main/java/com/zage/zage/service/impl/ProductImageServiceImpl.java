package com.zage.zage.service.impl;

import com.zage.zage.dto.ProductImageDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.ProductImageRepository;
import com.zage.zage.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Override
    public ProductImageDto createProductImage(ProductImageDto dto) {
        return productImageRepository.insertProductImage(dto);
    }

    @Override
    public ProductImageDto getProductImageById(Integer id) {
        return productImageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Image not found")
        );
    }

    @Override
    public List<ProductImageDto> getAllProductImages() {
        return productImageRepository.findAll();
    }

    @Override
    public ProductImageDto updateProductImage(Integer id, ProductImageDto dto) {
        getProductImageById(id);
        return productImageRepository.updateProductImage(id, dto);
    }

    @Override
    public void deleteProductImage(Integer id) {
        getProductImageById(id);
        productImageRepository.deleteProductImage(id);
    }
}
