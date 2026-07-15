package com.zage.zage.service.impl;

import com.zage.zage.dto.MeasurementTypeDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.MeasurementTypeRepository;
import com.zage.zage.service.MeasurementTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementTypeServiceImpl implements MeasurementTypeService {

    private final MeasurementTypeRepository measurementTypeRepository;

    @Override
    public MeasurementTypeDto createMeasurementType(MeasurementTypeDto dto) {
        return measurementTypeRepository.insertMeasurementType(dto);
    }

    @Override
    public MeasurementTypeDto getMeasurementTypeById(Integer id) {
        return measurementTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Measurement Type not found")
        );
    }

    @Override
    public List<MeasurementTypeDto> getAllMeasurementTypes() {
        return measurementTypeRepository.findAll();
    }

    @Override
    public MeasurementTypeDto updateMeasurementType(Integer id, MeasurementTypeDto dto) {
        getMeasurementTypeById(id);
        return measurementTypeRepository.updateMeasurementType(id, dto);
    }

    @Override
    public void deleteMeasurementType(Integer id) {
        getMeasurementTypeById(id);
        measurementTypeRepository.deleteMeasurementType(id);
    }
}
