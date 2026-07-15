package com.zage.zage.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MeasurementValueDto {
    private Integer id;
    private BigDecimal value;
    private String unit;
    private Integer measurementProfilesId;
    private Integer measurementProfilesCustomersId;
    private Integer measurementProfilesCustomersUsersId;
    private Integer measurementTypesId;
}
