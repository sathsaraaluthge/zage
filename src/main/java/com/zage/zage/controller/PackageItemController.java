package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.PackageItemDto;
import com.zage.zage.service.PackageItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/package-item")
@RequiredArgsConstructor
public class PackageItemController {

    private final PackageItemService packageItemService;

    @PostMapping
    public ResponseEntity<ApiResponse<PackageItemDto>> createPackageItem(@RequestBody PackageItemDto dto) {
        PackageItemDto createdItem = packageItemService.createPackageItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Package Item created successfully", createdItem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PackageItemDto>> getPackageItemById(@PathVariable Integer id) {
        PackageItemDto item = packageItemService.getPackageItemById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Package Item fetched successfully", item));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PackageItemDto>>> getAllPackageItems() {
        List<PackageItemDto> items = packageItemService.getAllPackageItems();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Package Items fetched successfully", items));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PackageItemDto>> updatePackageItem(
            @PathVariable Integer id,
            @RequestBody PackageItemDto dto) {
        PackageItemDto updatedItem = packageItemService.updatePackageItem(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Package Item updated successfully", updatedItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePackageItem(@PathVariable Integer id) {
        packageItemService.deletePackageItem(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Package Item deleted successfully", null));
    }
}
