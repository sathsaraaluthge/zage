package com.zage.zage.service.impl;

import com.zage.zage.dto.OrderItemDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.OrderItemRepository;
import com.zage.zage.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItemDto createOrderItem(OrderItemDto dto) {
        return orderItemRepository.insertOrderItem(dto);
    }

    @Override
    public OrderItemDto getOrderItemById(Integer id) {
        return orderItemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order Item not found")
        );
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItemDto updateOrderItem(Integer id, OrderItemDto dto) {
        getOrderItemById(id);
        return orderItemRepository.updateOrderItem(id, dto);
    }

    @Override
    public void deleteOrderItem(Integer id) {
        getOrderItemById(id);
        orderItemRepository.deleteOrderItem(id);
    }
}
