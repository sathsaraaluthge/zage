package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.WeddingPackageDto;
import com.zage.zage.service.WeddingPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wedding-package")
@RequiredArgsConstructor
public class WeddingPackageController {

    private final WeddingPackageService weddingPackageService;

    @PostMapping
    public ResponseEntity<ApiResponse<WeddingPackageDto>> createWeddingPackage(@RequestBody WeddingPackageDto dto) {
        WeddingPackageDto createdPackage = weddingPackageService.createWeddingPackage(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Wedding Package created successfully", createdPackage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WeddingPackageDto>> getWeddingPackageById(@PathVariable Integer id) {
        WeddingPackageDto pkg = weddingPackageService.getWeddingPackageById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Wedding Package fetched successfully", pkg));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WeddingPackageDto>>> getAllWeddingPackages() {
        List<WeddingPackageDto> packages = weddingPackageService.getAllWeddingPackages();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Wedding Packages fetched successfully", packages));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WeddingPackageDto>> updateWeddingPackage(
            @PathVariable Integer id,
            @RequestBody WeddingPackageDto dto) {
        WeddingPackageDto updatedPackage = weddingPackageService.updateWeddingPackage(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Wedding Package updated successfully", updatedPackage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteWeddingPackage(@PathVariable Integer id) {
        weddingPackageService.deleteWeddingPackage(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Wedding Package deleted successfully", null));
    }
}
