package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.CustomizationOptionDto;
import com.zage.zage.service.CustomizationOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customization-option")
@RequiredArgsConstructor
public class CustomizationOptionController {

    private final CustomizationOptionService customizationOptionService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomizationOptionDto>> createCustomizationOption(@RequestBody CustomizationOptionDto dto) {
        CustomizationOptionDto createdOption = customizationOptionService.createCustomizationOption(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Customization Option created successfully", createdOption));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomizationOptionDto>> getCustomizationOptionById(@PathVariable Integer id) {
        CustomizationOptionDto option = customizationOptionService.getCustomizationOptionById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customization Option fetched successfully", option));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomizationOptionDto>>> getAllCustomizationOptions() {
        List<CustomizationOptionDto> options = customizationOptionService.getAllCustomizationOptions();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customization Options fetched successfully", options));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomizationOptionDto>> updateCustomizationOption(
            @PathVariable Integer id,
            @RequestBody CustomizationOptionDto dto) {
        CustomizationOptionDto updatedOption = customizationOptionService.updateCustomizationOption(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customization Option updated successfully", updatedOption));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomizationOption(@PathVariable Integer id) {
        customizationOptionService.deleteCustomizationOption(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Customization Option deleted successfully", null));
    }
}
