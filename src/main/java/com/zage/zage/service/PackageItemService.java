package com.zage.zage.service;

import com.zage.zage.dto.PackageItemDto;

import java.util.List;

public interface PackageItemService {
    PackageItemDto createPackageItem(PackageItemDto dto);
    PackageItemDto getPackageItemById(Integer id);
    List<PackageItemDto> getAllPackageItems();
    PackageItemDto updatePackageItem(Integer id, PackageItemDto dto);
    void deletePackageItem(Integer id);
}
