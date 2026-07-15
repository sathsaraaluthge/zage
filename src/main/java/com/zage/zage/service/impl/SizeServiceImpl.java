package com.zage.zage.service.impl;

import com.zage.zage.dto.SizeDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.SizeRepository;
import com.zage.zage.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Override
    public SizeDto createSize(SizeDto dto) {
        return sizeRepository.insertSize(dto);
    }

    @Override
    public SizeDto getSizeById(Integer id) {
        return sizeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Size not found")
        );
    }

    @Override
    public List<SizeDto> getAllSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public SizeDto updateSize(Integer id, SizeDto dto) {
        getSizeById(id);
        return sizeRepository.updateSize(id, dto);
    }

    @Override
    public void deleteSize(Integer id) {
        getSizeById(id);
        sizeRepository.deleteSize(id);
    }
}
