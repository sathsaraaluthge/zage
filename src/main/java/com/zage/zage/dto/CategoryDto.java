package com.zage.zage.dto;

import com.zage.zage.enums.Status;
import lombok.Data;

@Data
public class CategoryDto {
    private Integer id;
    private String name;
    private String description;
    private Status status;
}
