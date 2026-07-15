package com.zage.zage.service;

import com.zage.zage.dto.ProductionOrderDto;

import java.util.List;

public interface ProductionOrderService {
    ProductionOrderDto createProductionOrder(ProductionOrderDto dto);
    ProductionOrderDto getProductionOrderById(Integer id);
    List<ProductionOrderDto> getAllProductionOrders();
    ProductionOrderDto updateProductionOrder(Integer id, ProductionOrderDto dto);
    void deleteProductionOrder(Integer id);
}
