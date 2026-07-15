package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.ProductImageDto;
import com.zage.zage.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-image")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductImageDto>> createProductImage(@RequestBody ProductImageDto dto) {
        ProductImageDto createdImage = productImageService.createProductImage(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Product Image created successfully", createdImage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductImageDto>> getProductImageById(@PathVariable Integer id) {
        ProductImageDto image = productImageService.getProductImageById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product Image fetched successfully", image));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductImageDto>>> getAllProductImages() {
        List<ProductImageDto> images = productImageService.getAllProductImages();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product Images fetched successfully", images));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductImageDto>> updateProductImage(
            @PathVariable Integer id,
            @RequestBody ProductImageDto dto) {
        ProductImageDto updatedImage = productImageService.updateProductImage(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product Image updated successfully", updatedImage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductImage(@PathVariable Integer id) {
        productImageService.deleteProductImage(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product Image deleted successfully", null));
    }
}
