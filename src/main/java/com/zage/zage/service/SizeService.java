package com.zage.zage.service;

import com.zage.zage.dto.SizeDto;

import java.util.List;

public interface SizeService {
    SizeDto createSize(SizeDto dto);
    SizeDto getSizeById(Integer id);
    List<SizeDto> getAllSizes();
    SizeDto updateSize(Integer id, SizeDto dto);
    void deleteSize(Integer id);
}
