package com.zage.zage.service.impl;

import com.zage.zage.dto.OrderDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.OrderRepository;
import com.zage.zage.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto dto) {
        return orderRepository.insertOrder(dto);
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found")
        );
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderDto updateOrder(Integer id, OrderDto dto) {
        getOrderById(id);
        return orderRepository.updateOrder(id, dto);
    }

    @Override
    public void deleteOrder(Integer id) {
        getOrderById(id);
        orderRepository.deleteOrder(id);
    }
}
