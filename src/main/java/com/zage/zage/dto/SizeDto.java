package com.zage.zage.dto;

import lombok.Data;

@Data
public class SizeDto {
    private Integer id;
    private String name;
    private String type; // ENUM from database mapped as String
}
