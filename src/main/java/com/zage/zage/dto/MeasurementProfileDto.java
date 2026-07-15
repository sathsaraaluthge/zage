package com.zage.zage.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MeasurementProfileDto {
    private Integer id;
    private String name;
    private Boolean isDefault;
    private LocalDateTime createdAt;
    private Integer customersId;
    private Integer customersUsersId;
}
