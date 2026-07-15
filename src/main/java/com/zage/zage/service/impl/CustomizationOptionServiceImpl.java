package com.zage.zage.service.impl;

import com.zage.zage.dto.CustomizationOptionDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.CustomizationOptionRepository;
import com.zage.zage.service.CustomizationOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomizationOptionServiceImpl implements CustomizationOptionService {

    private final CustomizationOptionRepository customizationOptionRepository;

    @Override
    public CustomizationOptionDto createCustomizationOption(CustomizationOptionDto dto) {
        return customizationOptionRepository.insertCustomizationOption(dto);
    }

    @Override
    public CustomizationOptionDto getCustomizationOptionById(Integer id) {
        return customizationOptionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customization Option not found")
        );
    }

    @Override
    public List<CustomizationOptionDto> getAllCustomizationOptions() {
        return customizationOptionRepository.findAll();
    }

    @Override
    public CustomizationOptionDto updateCustomizationOption(Integer id, CustomizationOptionDto dto) {
        getCustomizationOptionById(id);
        return customizationOptionRepository.updateCustomizationOption(id, dto);
    }

    @Override
    public void deleteCustomizationOption(Integer id) {
        getCustomizationOptionById(id);
        customizationOptionRepository.deleteCustomizationOption(id);
    }
}
