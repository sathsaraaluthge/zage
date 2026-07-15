package com.zage.zage.service;

import com.zage.zage.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto dto);
    OrderDto getOrderById(Integer id);
    List<OrderDto> getAllOrders();
    OrderDto updateOrder(Integer id, OrderDto dto);
    void deleteOrder(Integer id);
}
