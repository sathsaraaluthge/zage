package com.zage.zage.service.impl;

import com.zage.zage.dto.MaterialDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.MaterialRepository;
import com.zage.zage.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public MaterialDto createMaterial(MaterialDto dto) {
        return materialRepository.insertMaterial(dto);
    }

    @Override
    public MaterialDto getMaterialById(Integer id) {
        return materialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Material not found")
        );
    }

    @Override
    public List<MaterialDto> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public MaterialDto updateMaterial(Integer id, MaterialDto dto) {
        getMaterialById(id);
        return materialRepository.updateMaterial(id, dto);
    }

    @Override
    public void deleteMaterial(Integer id) {
        getMaterialById(id);
        materialRepository.deleteMaterial(id);
    }
}
