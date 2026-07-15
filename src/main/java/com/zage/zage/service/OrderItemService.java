package com.zage.zage.service;

import com.zage.zage.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    OrderItemDto createOrderItem(OrderItemDto dto);
    OrderItemDto getOrderItemById(Integer id);
    List<OrderItemDto> getAllOrderItems();
    OrderItemDto updateOrderItem(Integer id, OrderItemDto dto);
    void deleteOrderItem(Integer id);
}
