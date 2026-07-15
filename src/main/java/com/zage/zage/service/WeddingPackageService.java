package com.zage.zage.service;

import com.zage.zage.dto.WeddingPackageDto;

import java.util.List;

public interface WeddingPackageService {
    WeddingPackageDto createWeddingPackage(WeddingPackageDto dto);
    WeddingPackageDto getWeddingPackageById(Integer id);
    List<WeddingPackageDto> getAllWeddingPackages();
    WeddingPackageDto updateWeddingPackage(Integer id, WeddingPackageDto dto);
    void deleteWeddingPackage(Integer id);
}
