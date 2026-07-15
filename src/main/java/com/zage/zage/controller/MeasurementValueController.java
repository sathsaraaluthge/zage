package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.MeasurementValueDto;
import com.zage.zage.service.MeasurementValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurement-value")
@RequiredArgsConstructor
public class MeasurementValueController {

    private final MeasurementValueService measurementValueService;

    @PostMapping
    public ResponseEntity<ApiResponse<MeasurementValueDto>> createMeasurementValue(@RequestBody MeasurementValueDto dto) {
        MeasurementValueDto createdValue = measurementValueService.createMeasurementValue(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Measurement Value created successfully", createdValue));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MeasurementValueDto>> getMeasurementValueById(@PathVariable Integer id) {
        MeasurementValueDto value = measurementValueService.getMeasurementValueById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Value fetched successfully", value));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MeasurementValueDto>>> getAllMeasurementValues() {
        List<MeasurementValueDto> values = measurementValueService.getAllMeasurementValues();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Values fetched successfully", values));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MeasurementValueDto>> updateMeasurementValue(
            @PathVariable Integer id,
            @RequestBody MeasurementValueDto dto) {
        MeasurementValueDto updatedValue = measurementValueService.updateMeasurementValue(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Value updated successfully", updatedValue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMeasurementValue(@PathVariable Integer id) {
        measurementValueService.deleteMeasurementValue(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Value deleted successfully", null));
    }
}
