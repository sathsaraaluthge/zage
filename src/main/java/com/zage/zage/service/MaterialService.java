package com.zage.zage.service;

import com.zage.zage.dto.MaterialDto;

import java.util.List;

public interface MaterialService {
    MaterialDto createMaterial(MaterialDto dto);
    MaterialDto getMaterialById(Integer id);
    List<MaterialDto> getAllMaterials();
    MaterialDto updateMaterial(Integer id, MaterialDto dto);
    void deleteMaterial(Integer id);
}
