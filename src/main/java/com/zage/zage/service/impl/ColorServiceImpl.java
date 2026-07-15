package com.zage.zage.service.impl;

import com.zage.zage.dto.ColorDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.ColorRepository;
import com.zage.zage.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public ColorDto createColor(ColorDto dto) {
        return colorRepository.insertColor(dto);
    }

    @Override
    public ColorDto getColorById(Integer id) {
        return colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Color not found")
        );
    }

    @Override
    public List<ColorDto> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public ColorDto updateColor(Integer id, ColorDto dto) {
        getColorById(id);
        return colorRepository.updateColor(id, dto);
    }

    @Override
    public void deleteColor(Integer id) {
        getColorById(id);
        colorRepository.deleteColor(id);
    }
}
