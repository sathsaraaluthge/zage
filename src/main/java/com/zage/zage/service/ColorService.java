package com.zage.zage.service;

import com.zage.zage.dto.ColorDto;

import java.util.List;

public interface ColorService {
    ColorDto createColor(ColorDto dto);
    ColorDto getColorById(Integer id);
    List<ColorDto> getAllColors();
    ColorDto updateColor(Integer id, ColorDto dto);
    void deleteColor(Integer id);
}
