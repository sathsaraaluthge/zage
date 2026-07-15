package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.MeasurementProfileDto;
import com.zage.zage.service.MeasurementProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurement-profile")
@RequiredArgsConstructor
public class MeasurementProfileController {

    private final MeasurementProfileService measurementProfileService;

    @PostMapping
    public ResponseEntity<ApiResponse<MeasurementProfileDto>> createMeasurementProfile(@RequestBody MeasurementProfileDto dto) {
        MeasurementProfileDto createdProfile = measurementProfileService.createMeasurementProfile(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Measurement Profile created successfully", createdProfile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MeasurementProfileDto>> getMeasurementProfileById(@PathVariable Integer id) {
        MeasurementProfileDto profile = measurementProfileService.getMeasurementProfileById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Profile fetched successfully", profile));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MeasurementProfileDto>>> getAllMeasurementProfiles() {
        List<MeasurementProfileDto> profiles = measurementProfileService.getAllMeasurementProfiles();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Profiles fetched successfully", profiles));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MeasurementProfileDto>> updateMeasurementProfile(
            @PathVariable Integer id,
            @RequestBody MeasurementProfileDto dto) {
        MeasurementProfileDto updatedProfile = measurementProfileService.updateMeasurementProfile(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Profile updated successfully", updatedProfile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMeasurementProfile(@PathVariable Integer id) {
        measurementProfileService.deleteMeasurementProfile(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Measurement Profile deleted successfully", null));
    }
}
