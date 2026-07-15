package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.OrderItemDto;
import com.zage.zage.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-item")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderItemDto>> createOrderItem(@RequestBody OrderItemDto dto) {
        OrderItemDto createdItem = orderItemService.createOrderItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Order Item created successfully", createdItem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderItemDto>> getOrderItemById(@PathVariable Integer id) {
        OrderItemDto item = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Order Item fetched successfully", item));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderItemDto>>> getAllOrderItems() {
        List<OrderItemDto> items = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Order Items fetched successfully", items));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderItemDto>> updateOrderItem(
            @PathVariable Integer id,
            @RequestBody OrderItemDto dto) {
        OrderItemDto updatedItem = orderItemService.updateOrderItem(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Order Item updated successfully", updatedItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrderItem(@PathVariable Integer id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Order Item deleted successfully", null));
    }
}
