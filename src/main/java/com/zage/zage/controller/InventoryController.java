package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.InventoryDto;
import com.zage.zage.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<InventoryDto>> createInventory(@RequestBody InventoryDto dto) {
        InventoryDto createdInventory = inventoryService.createInventory(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Inventory created successfully", createdInventory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryDto>> getInventoryById(@PathVariable Integer id) {
        InventoryDto inventory = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Inventory fetched successfully", inventory));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InventoryDto>>> getAllInventory() {
        List<InventoryDto> inventoryList = inventoryService.getAllInventory();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Inventory fetched successfully", inventoryList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryDto>> updateInventory(
            @PathVariable Integer id,
            @RequestBody InventoryDto dto) {
        InventoryDto updatedInventory = inventoryService.updateInventory(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Inventory updated successfully", updatedInventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Inventory deleted successfully", null));
    }
}
