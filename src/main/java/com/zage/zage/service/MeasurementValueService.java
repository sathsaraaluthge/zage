package com.zage.zage.service;

import com.zage.zage.dto.MeasurementValueDto;

import java.util.List;

public interface MeasurementValueService {
    MeasurementValueDto createMeasurementValue(MeasurementValueDto dto);
    MeasurementValueDto getMeasurementValueById(Integer id);
    List<MeasurementValueDto> getAllMeasurementValues();
    MeasurementValueDto updateMeasurementValue(Integer id, MeasurementValueDto dto);
    void deleteMeasurementValue(Integer id);
}
