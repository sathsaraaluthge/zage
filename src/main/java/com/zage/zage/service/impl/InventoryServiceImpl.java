package com.zage.zage.service.impl;

import com.zage.zage.dto.InventoryDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.InventoryRepository;
import com.zage.zage.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public InventoryDto createInventory(InventoryDto dto) {
        return inventoryRepository.insertInventory(dto);
    }

    @Override
    public InventoryDto getInventoryById(Integer id) {
        return inventoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Inventory not found")
        );
    }

    @Override
    public List<InventoryDto> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public InventoryDto updateInventory(Integer id, InventoryDto dto) {
        getInventoryById(id);
        return inventoryRepository.updateInventory(id, dto);
    }

    @Override
    public void deleteInventory(Integer id) {
        getInventoryById(id);
        inventoryRepository.deleteInventory(id);
    }
}
