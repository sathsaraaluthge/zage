package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.CustomerRequestDto;
import com.zage.zage.dto.CustomerResponseDto;
import com.zage.zage.dto.ProductDto;
import com.zage.zage.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<ProductDto>> createProduct(@RequestBody ProductDto dto) {
        ProductDto createdCustomer = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Product created successfully", createdCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> updateProduct(
            @PathVariable Integer id,
            @RequestBody ProductDto dto) {
        ProductDto updatedProduct = productService.updateProduct(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product updated successfully", updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(
            @PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> getProductById(
            @PathVariable Integer id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Product fetched successfully", product));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> searchProducts(
            @RequestParam (required = false) String name,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) java.math.BigDecimal minPrice,
            @RequestParam(required = false) java.math.BigDecimal maxPrice) {
        List<ProductDto> products = productService.searchProducts(name, status, categoryId, productType, minPrice, maxPrice);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Products fetched successfully", products));
    }

}
