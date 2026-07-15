package com.zage.zage.service.impl;

import com.zage.zage.dto.ProductVariantDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.ProductVariantRepository;
import com.zage.zage.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;

    @Override
    public ProductVariantDto createProductVariant(ProductVariantDto dto) {
        return productVariantRepository.insertProductVariant(dto);
    }

    @Override
    public ProductVariantDto getProductVariantById(Integer id) {
        return productVariantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Variant not found")
        );
    }

    @Override
    public List<ProductVariantDto> getAllProductVariants() {
        return productVariantRepository.findAll();
    }

    @Override
    public ProductVariantDto updateProductVariant(Integer id, ProductVariantDto dto) {
        getProductVariantById(id);
        return productVariantRepository.updateProductVariant(id, dto);
    }

    @Override
    public void deleteProductVariant(Integer id) {
        getProductVariantById(id);
        productVariantRepository.deleteProductVariant(id);
    }
}
