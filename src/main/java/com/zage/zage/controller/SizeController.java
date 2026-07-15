package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.SizeDto;
import com.zage.zage.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/size")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    @PostMapping
    public ResponseEntity<ApiResponse<SizeDto>> createSize(@RequestBody SizeDto dto) {
        SizeDto createdSize = sizeService.createSize(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Size created successfully", createdSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SizeDto>> getSizeById(@PathVariable Integer id) {
        SizeDto size = sizeService.getSizeById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Size fetched successfully", size));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SizeDto>>> getAllSizes() {
        List<SizeDto> sizes = sizeService.getAllSizes();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Sizes fetched successfully", sizes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SizeDto>> updateSize(
            @PathVariable Integer id,
            @RequestBody SizeDto dto) {
        SizeDto updatedSize = sizeService.updateSize(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Size updated successfully", updatedSize));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSize(@PathVariable Integer id) {
        sizeService.deleteSize(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Size deleted successfully", null));
    }
}
