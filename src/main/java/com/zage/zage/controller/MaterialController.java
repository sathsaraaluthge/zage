package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.MaterialDto;
import com.zage.zage.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping
    public ResponseEntity<ApiResponse<MaterialDto>> createMaterial(@RequestBody MaterialDto dto) {
        MaterialDto createdMaterial = materialService.createMaterial(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Material created successfully", createdMaterial));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaterialDto>> getMaterialById(@PathVariable Integer id) {
        MaterialDto material = materialService.getMaterialById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Material fetched successfully", material));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MaterialDto>>> getAllMaterials() {
        List<MaterialDto> materials = materialService.getAllMaterials();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Materials fetched successfully", materials));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MaterialDto>> updateMaterial(
            @PathVariable Integer id,
            @RequestBody MaterialDto dto) {
        MaterialDto updatedMaterial = materialService.updateMaterial(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Material updated successfully", updatedMaterial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMaterial(@PathVariable Integer id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Material deleted successfully", null));
    }
}
