package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.ProductionOrderDto;
import com.zage.zage.service.ProductionOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production-order")
@RequiredArgsConstructor
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductionOrderDto>> createProductionOrder(@RequestBody ProductionOrderDto dto) {
        ProductionOrderDto createdOrder = productionOrderService.createProductionOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Production Order created successfully", createdOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductionOrderDto>> getProductionOrderById(@PathVariable Integer id) {
        ProductionOrderDto order = productionOrderService.getProductionOrderById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Production Order fetched successfully", order));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductionOrderDto>>> getAllProductionOrders() {
        List<ProductionOrderDto> orders = productionOrderService.getAllProductionOrders();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Production Orders fetched successfully", orders));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductionOrderDto>> updateProductionOrder(
            @PathVariable Integer id,
            @RequestBody ProductionOrderDto dto) {
        ProductionOrderDto updatedOrder = productionOrderService.updateProductionOrder(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Production Order updated successfully", updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductionOrder(@PathVariable Integer id) {
        productionOrderService.deleteProductionOrder(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Production Order deleted successfully", null));
    }
}
