package com.zage.zage.service;

import com.zage.zage.dto.CustomOrderOptionDto;

import java.util.List;

public interface CustomOrderOptionService {
    CustomOrderOptionDto createCustomOrderOption(CustomOrderOptionDto dto);
    CustomOrderOptionDto getCustomOrderOptionById(Integer id);
    List<CustomOrderOptionDto> getAllCustomOrderOptions();
    CustomOrderOptionDto updateCustomOrderOption(Integer id, CustomOrderOptionDto dto);
    void deleteCustomOrderOption(Integer id);
}
