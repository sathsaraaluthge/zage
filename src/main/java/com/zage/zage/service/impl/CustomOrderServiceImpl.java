package com.zage.zage.service.impl;

import com.zage.zage.dto.CustomOrderDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.CustomOrderRepository;
import com.zage.zage.service.CustomOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOrderServiceImpl implements CustomOrderService {

    private final CustomOrderRepository customOrderRepository;

    @Override
    public CustomOrderDto createCustomOrder(CustomOrderDto dto) {
        return customOrderRepository.insertCustomOrder(dto);
    }

    @Override
    public CustomOrderDto getCustomOrderById(Integer id) {
        return customOrderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Custom Order not found")
        );
    }

    @Override
    public List<CustomOrderDto> getAllCustomOrders() {
        return customOrderRepository.findAll();
    }

    @Override
    public CustomOrderDto updateCustomOrder(Integer id, CustomOrderDto dto) {
        getCustomOrderById(id);
        return customOrderRepository.updateCustomOrder(id, dto);
    }

    @Override
    public void deleteCustomOrder(Integer id) {
        getCustomOrderById(id);
        customOrderRepository.deleteCustomOrder(id);
    }
}
