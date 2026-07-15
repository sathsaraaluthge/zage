package com.zage.zage.service;

import com.zage.zage.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto dto);
    InventoryDto getInventoryById(Integer id);
    List<InventoryDto> getAllInventory();
    InventoryDto updateInventory(Integer id, InventoryDto dto);
    void deleteInventory(Integer id);
}
