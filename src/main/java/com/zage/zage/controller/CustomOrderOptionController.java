package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.CustomOrderOptionDto;
import com.zage.zage.service.CustomOrderOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/custom-order-option")
@RequiredArgsConstructor
public class CustomOrderOptionController {

    private final CustomOrderOptionService customOrderOptionService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomOrderOptionDto>> createCustomOrderOption(@RequestBody CustomOrderOptionDto dto) {
        CustomOrderOptionDto createdOption = customOrderOptionService.createCustomOrderOption(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Custom Order Option created successfully", createdOption));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomOrderOptionDto>> getCustomOrderOptionById(@PathVariable Integer id) {
        CustomOrderOptionDto option = customOrderOptionService.getCustomOrderOptionById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Custom Order Option fetched successfully", option));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomOrderOptionDto>>> getAllCustomOrderOptions() {
        List<CustomOrderOptionDto> options = customOrderOptionService.getAllCustomOrderOptions();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Custom Order Options fetched successfully", options));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomOrderOptionDto>> updateCustomOrderOption(
            @PathVariable Integer id,
            @RequestBody CustomOrderOptionDto dto) {
        CustomOrderOptionDto updatedOption = customOrderOptionService.updateCustomOrderOption(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Custom Order Option updated successfully", updatedOption));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomOrderOption(@PathVariable Integer id) {
        customOrderOptionService.deleteCustomOrderOption(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Custom Order Option deleted successfully", null));
    }
}
