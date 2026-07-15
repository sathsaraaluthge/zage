package com.zage.zage.service;

import com.zage.zage.dto.MeasurementProfileDto;

import java.util.List;

public interface MeasurementProfileService {
    MeasurementProfileDto createMeasurementProfile(MeasurementProfileDto dto);
    MeasurementProfileDto getMeasurementProfileById(Integer id);
    List<MeasurementProfileDto> getAllMeasurementProfiles();
    MeasurementProfileDto updateMeasurementProfile(Integer id, MeasurementProfileDto dto);
    void deleteMeasurementProfile(Integer id);
}
