package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.ProductVariantDto;
import com.zage.zage.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-variant")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductVariantDto>> createProductVariant(@RequestBody ProductVariantDto dto) {
        ProductVariantDto createdVariant = productVariantService.createProductVariant(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Product Variant created successfully", createdVariant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariantDto>> getProductVariantById(@PathVariable Integer id) {
        ProductVariantDto variant = productVariantService.getProductVariantById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product Variant fetched successfully", variant));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariantDto>>> getAllProductVariants() {
        List<ProductVariantDto> variants = productVariantService.getAllProductVariants();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product Variants fetched successfully", variants));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariantDto>> updateProductVariant(
            @PathVariable Integer id,
            @RequestBody ProductVariantDto dto) {
        ProductVariantDto updatedVariant = productVariantService.updateProductVariant(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product Variant updated successfully", updatedVariant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductVariant(@PathVariable Integer id) {
        productVariantService.deleteProductVariant(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product Variant deleted successfully", null));
    }
}
