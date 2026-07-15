package com.zage.zage.service;

import com.zage.zage.dto.CustomOrderDto;

import java.util.List;

public interface CustomOrderService {
    CustomOrderDto createCustomOrder(CustomOrderDto dto);
    CustomOrderDto getCustomOrderById(Integer id);
    List<CustomOrderDto> getAllCustomOrders();
    CustomOrderDto updateCustomOrder(Integer id, CustomOrderDto dto);
    void deleteCustomOrder(Integer id);
}
