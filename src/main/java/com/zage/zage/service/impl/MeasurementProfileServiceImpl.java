package com.zage.zage.service.impl;

import com.zage.zage.dto.MeasurementProfileDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.MeasurementProfileRepository;
import com.zage.zage.service.MeasurementProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementProfileServiceImpl implements MeasurementProfileService {

    private final MeasurementProfileRepository measurementProfileRepository;

    @Override
    public MeasurementProfileDto createMeasurementProfile(MeasurementProfileDto dto) {
        return measurementProfileRepository.insertMeasurementProfile(dto);
    }

    @Override
    public MeasurementProfileDto getMeasurementProfileById(Integer id) {
        return measurementProfileRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Measurement Profile not found")
        );
    }

    @Override
    public List<MeasurementProfileDto> getAllMeasurementProfiles() {
        return measurementProfileRepository.findAll();
    }

    @Override
    public MeasurementProfileDto updateMeasurementProfile(Integer id, MeasurementProfileDto dto) {
        getMeasurementProfileById(id);
        return measurementProfileRepository.updateMeasurementProfile(id, dto);
    }

    @Override
    public void deleteMeasurementProfile(Integer id) {
        getMeasurementProfileById(id);
        measurementProfileRepository.deleteMeasurementProfile(id);
    }
}
