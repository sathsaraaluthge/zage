package com.zage.zage.service.impl;

import com.zage.zage.dto.CustomizationValueDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.CustomizationValueRepository;
import com.zage.zage.service.CustomizationValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomizationValueServiceImpl implements CustomizationValueService {

    private final CustomizationValueRepository customizationValueRepository;

    @Override
    public CustomizationValueDto createCustomizationValue(CustomizationValueDto dto) {
        return customizationValueRepository.insertCustomizationValue(dto);
    }

    @Override
    public CustomizationValueDto getCustomizationValueById(Integer id) {
        return customizationValueRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customization Value not found")
        );
    }

    @Override
    public List<CustomizationValueDto> getAllCustomizationValues() {
        return customizationValueRepository.findAll();
    }

    @Override
    public CustomizationValueDto updateCustomizationValue(Integer id, CustomizationValueDto dto) {
        getCustomizationValueById(id);
        return customizationValueRepository.updateCustomizationValue(id, dto);
    }

    @Override
    public void deleteCustomizationValue(Integer id) {
        getCustomizationValueById(id);
        customizationValueRepository.deleteCustomizationValue(id);
    }
}
