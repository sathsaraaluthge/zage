package com.zage.zage.service.impl;

import com.zage.zage.dto.PackageItemDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.PackageItemRepository;
import com.zage.zage.service.PackageItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageItemServiceImpl implements PackageItemService {

    private final PackageItemRepository packageItemRepository;

    @Override
    public PackageItemDto createPackageItem(PackageItemDto dto) {
        return packageItemRepository.insertPackageItem(dto);
    }

    @Override
    public PackageItemDto getPackageItemById(Integer id) {
        return packageItemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Package Item not found")
        );
    }

    @Override
    public List<PackageItemDto> getAllPackageItems() {
        return packageItemRepository.findAll();
    }

    @Override
    public PackageItemDto updatePackageItem(Integer id, PackageItemDto dto) {
        getPackageItemById(id);
        return packageItemRepository.updatePackageItem(id, dto);
    }

    @Override
    public void deletePackageItem(Integer id) {
        getPackageItemById(id);
        packageItemRepository.deletePackageItem(id);
    }
}
