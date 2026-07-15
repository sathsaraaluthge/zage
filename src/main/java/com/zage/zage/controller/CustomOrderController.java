package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.CustomOrderDto;
import com.zage.zage.service.CustomOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/custom-order")
@RequiredArgsConstructor
public class CustomOrderController {

    private final CustomOrderService customOrderService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomOrderDto>> createCustomOrder(@RequestBody CustomOrderDto dto) {
        CustomOrderDto createdOrder = customOrderService.createCustomOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Custom Order created successfully", createdOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomOrderDto>> getCustomOrderById(@PathVariable Integer id) {
        CustomOrderDto order = customOrderService.getCustomOrderById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Custom Order fetched successfully", order));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomOrderDto>>> getAllCustomOrders() {
        List<CustomOrderDto> orders = customOrderService.getAllCustomOrders();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Custom Orders fetched successfully", orders));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomOrderDto>> updateCustomOrder(
            @PathVariable Integer id,
            @RequestBody CustomOrderDto dto) {
        CustomOrderDto updatedOrder = customOrderService.updateCustomOrder(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Custom Order updated successfully", updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomOrder(@PathVariable Integer id) {
        customOrderService.deleteCustomOrder(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Custom Order deleted successfully", null));
    }
}
