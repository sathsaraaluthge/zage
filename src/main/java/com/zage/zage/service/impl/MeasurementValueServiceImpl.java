package com.zage.zage.service.impl;

import com.zage.zage.dto.MeasurementValueDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.MeasurementValueRepository;
import com.zage.zage.service.MeasurementValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementValueServiceImpl implements MeasurementValueService {

    private final MeasurementValueRepository measurementValueRepository;

    @Override
    public MeasurementValueDto createMeasurementValue(MeasurementValueDto dto) {
        return measurementValueRepository.insertMeasurementValue(dto);
    }

    @Override
    public MeasurementValueDto getMeasurementValueById(Integer id) {
        return measurementValueRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Measurement Value not found")
        );
    }

    @Override
    public List<MeasurementValueDto> getAllMeasurementValues() {
        return measurementValueRepository.findAll();
    }

    @Override
    public MeasurementValueDto updateMeasurementValue(Integer id, MeasurementValueDto dto) {
        getMeasurementValueById(id);
        return measurementValueRepository.updateMeasurementValue(id, dto);
    }

    @Override
    public void deleteMeasurementValue(Integer id) {
        getMeasurementValueById(id);
        measurementValueRepository.deleteMeasurementValue(id);
    }
}
