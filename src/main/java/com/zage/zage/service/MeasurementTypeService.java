package com.zage.zage.service;

import com.zage.zage.dto.MeasurementTypeDto;

import java.util.List;

public interface MeasurementTypeService {
    MeasurementTypeDto createMeasurementType(MeasurementTypeDto dto);
    MeasurementTypeDto getMeasurementTypeById(Integer id);
    List<MeasurementTypeDto> getAllMeasurementTypes();
    MeasurementTypeDto updateMeasurementType(Integer id, MeasurementTypeDto dto);
    void deleteMeasurementType(Integer id);
}
