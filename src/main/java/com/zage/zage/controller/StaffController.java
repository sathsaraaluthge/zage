package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.StaffRequestDto;
import com.zage.zage.dto.StaffResponseDto;
import com.zage.zage.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<StaffResponseDto>> createStaff(@RequestBody StaffRequestDto dto) {
        StaffResponseDto createdStaff = staffService.createStaff(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Staff created successfully", createdStaff));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StaffResponseDto>> getStaff(@PathVariable Long id) {
        StaffResponseDto staff = staffService.getStaff(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Staff fetched successfully", staff));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StaffResponseDto>>> getStaffs() {
        List<StaffResponseDto> staff = staffService.getStaffs();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Staff fetched successfully", staff));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Staff deleted successfully", null));
    }
}
