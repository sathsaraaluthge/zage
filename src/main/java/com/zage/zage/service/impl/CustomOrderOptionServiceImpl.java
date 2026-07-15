package com.zage.zage.service.impl;

import com.zage.zage.dto.CustomOrderOptionDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.CustomOrderOptionRepository;
import com.zage.zage.service.CustomOrderOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOrderOptionServiceImpl implements CustomOrderOptionService {

    private final CustomOrderOptionRepository customOrderOptionRepository;

    @Override
    public CustomOrderOptionDto createCustomOrderOption(CustomOrderOptionDto dto) {
        return customOrderOptionRepository.insertCustomOrderOption(dto);
    }

    @Override
    public CustomOrderOptionDto getCustomOrderOptionById(Integer id) {
        return customOrderOptionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Custom Order Option not found")
        );
    }

    @Override
    public List<CustomOrderOptionDto> getAllCustomOrderOptions() {
        return customOrderOptionRepository.findAll();
    }

    @Override
    public CustomOrderOptionDto updateCustomOrderOption(Integer id, CustomOrderOptionDto dto) {
        getCustomOrderOptionById(id);
        return customOrderOptionRepository.updateCustomOrderOption(id, dto);
    }

    @Override
    public void deleteCustomOrderOption(Integer id) {
        getCustomOrderOptionById(id);
        customOrderOptionRepository.deleteCustomOrderOption(id);
    }
}
