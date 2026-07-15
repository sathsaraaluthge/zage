package com.zage.zage.service;

import com.zage.zage.dto.CustomizationOptionDto;

import java.util.List;

public interface CustomizationOptionService {
    CustomizationOptionDto createCustomizationOption(CustomizationOptionDto dto);
    CustomizationOptionDto getCustomizationOptionById(Integer id);
    List<CustomizationOptionDto> getAllCustomizationOptions();
    CustomizationOptionDto updateCustomizationOption(Integer id, CustomizationOptionDto dto);
    void deleteCustomizationOption(Integer id);
}
