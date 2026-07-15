package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.ColorDto;
import com.zage.zage.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @PostMapping
    public ResponseEntity<ApiResponse<ColorDto>> createColor(@RequestBody ColorDto dto) {
        ColorDto createdColor = colorService.createColor(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Color created successfully", createdColor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ColorDto>> getColorById(@PathVariable Integer id) {
        ColorDto color = colorService.getColorById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Color fetched successfully", color));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ColorDto>>> getAllColors() {
        List<ColorDto> colors = colorService.getAllColors();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Colors fetched successfully", colors));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ColorDto>> updateColor(
            @PathVariable Integer id,
            @RequestBody ColorDto dto) {
        ColorDto updatedColor = colorService.updateColor(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Color updated successfully", updatedColor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteColor(@PathVariable Integer id) {
        colorService.deleteColor(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Color deleted successfully", null));
    }
}
