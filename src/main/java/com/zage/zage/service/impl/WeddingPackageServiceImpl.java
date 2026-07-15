package com.zage.zage.service.impl;

import com.zage.zage.dto.WeddingPackageDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.WeddingPackageRepository;
import com.zage.zage.service.WeddingPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeddingPackageServiceImpl implements WeddingPackageService {

    private final WeddingPackageRepository weddingPackageRepository;

    @Override
    public WeddingPackageDto createWeddingPackage(WeddingPackageDto dto) {
        return weddingPackageRepository.insertWeddingPackage(dto);
    }

    @Override
    public WeddingPackageDto getWeddingPackageById(Integer id) {
        return weddingPackageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Wedding Package not found")
        );
    }

    @Override
    public List<WeddingPackageDto> getAllWeddingPackages() {
        return weddingPackageRepository.findAll();
    }

    @Override
    public WeddingPackageDto updateWeddingPackage(Integer id, WeddingPackageDto dto) {
        getWeddingPackageById(id);
        return weddingPackageRepository.updateWeddingPackage(id, dto);
    }

    @Override
    public void deleteWeddingPackage(Integer id) {
        getWeddingPackageById(id);
        weddingPackageRepository.deleteWeddingPackage(id);
    }
}
