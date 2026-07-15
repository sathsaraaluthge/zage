package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.CustomizationValueDto;
import com.zage.zage.service.CustomizationValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customization-value")
@RequiredArgsConstructor
public class CustomizationValueController {

    private final CustomizationValueService customizationValueService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomizationValueDto>> createCustomizationValue(@RequestBody CustomizationValueDto dto) {
        CustomizationValueDto createdValue = customizationValueService.createCustomizationValue(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Customization Value created successfully", createdValue));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomizationValueDto>> getCustomizationValueById(@PathVariable Integer id) {
        CustomizationValueDto value = customizationValueService.getCustomizationValueById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customization Value fetched successfully", value));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomizationValueDto>>> getAllCustomizationValues() {
        List<CustomizationValueDto> values = customizationValueService.getAllCustomizationValues();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customization Values fetched successfully", values));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomizationValueDto>> updateCustomizationValue(
            @PathVariable Integer id,
            @RequestBody CustomizationValueDto dto) {
        CustomizationValueDto updatedValue = customizationValueService.updateCustomizationValue(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customization Value updated successfully", updatedValue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomizationValue(@PathVariable Integer id) {
        customizationValueService.deleteCustomizationValue(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customization Value deleted successfully", null));
    }
}
