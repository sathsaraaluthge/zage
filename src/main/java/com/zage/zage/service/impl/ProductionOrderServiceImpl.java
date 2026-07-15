package com.zage.zage.service.impl;

import com.zage.zage.dto.ProductionOrderDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.ProductionOrderRepository;
import com.zage.zage.service.ProductionOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductionOrderServiceImpl implements ProductionOrderService {

    private final ProductionOrderRepository productionOrderRepository;

    @Override
    public ProductionOrderDto createProductionOrder(ProductionOrderDto dto) {
        return productionOrderRepository.insertProductionOrder(dto);
    }

    @Override
    public ProductionOrderDto getProductionOrderById(Integer id) {
        return productionOrderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Production Order not found")
        );
    }

    @Override
    public List<ProductionOrderDto> getAllProductionOrders() {
        return productionOrderRepository.findAll();
    }

    @Override
    public ProductionOrderDto updateProductionOrder(Integer id, ProductionOrderDto dto) {
        getProductionOrderById(id);
        return productionOrderRepository.updateProductionOrder(id, dto);
    }

    @Override
    public void deleteProductionOrder(Integer id) {
        getProductionOrderById(id);
        productionOrderRepository.deleteProductionOrder(id);
    }
}
