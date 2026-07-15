package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.MeasurementTypeDto;
import com.zage.zage.service.MeasurementTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurement-type")
@RequiredArgsConstructor
public class MeasurementTypeController {

    private final MeasurementTypeService measurementTypeService;

    @PostMapping
    public ResponseEntity<ApiResponse<MeasurementTypeDto>> createMeasurementType(@RequestBody MeasurementTypeDto dto) {
        MeasurementTypeDto createdType = measurementTypeService.createMeasurementType(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Measurement Type created successfully", createdType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MeasurementTypeDto>> getMeasurementTypeById(@PathVariable Integer id) {
        MeasurementTypeDto type = measurementTypeService.getMeasurementTypeById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Type fetched successfully", type));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MeasurementTypeDto>>> getAllMeasurementTypes() {
        List<MeasurementTypeDto> types = measurementTypeService.getAllMeasurementTypes();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Types fetched successfully", types));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MeasurementTypeDto>> updateMeasurementType(
            @PathVariable Integer id,
            @RequestBody MeasurementTypeDto dto) {
        MeasurementTypeDto updatedType = measurementTypeService.updateMeasurementType(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Type updated successfully", updatedType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMeasurementType(@PathVariable Integer id) {
        measurementTypeService.deleteMeasurementType(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Type deleted successfully", null));
    }
}
