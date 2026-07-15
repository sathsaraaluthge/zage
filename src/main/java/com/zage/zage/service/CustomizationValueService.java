package com.zage.zage.service;

import com.zage.zage.dto.CustomizationValueDto;

import java.util.List;

public interface CustomizationValueService {
    CustomizationValueDto createCustomizationValue(CustomizationValueDto dto);
    CustomizationValueDto getCustomizationValueById(Integer id);
    List<CustomizationValueDto> getAllCustomizationValues();
    CustomizationValueDto updateCustomizationValue(Integer id, CustomizationValueDto dto);
    void deleteCustomizationValue(Integer id);
}
