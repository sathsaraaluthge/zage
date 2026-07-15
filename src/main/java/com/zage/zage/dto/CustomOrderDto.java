package com.zage.zage.dto;

import com.zage.zage.enums.Status;
import lombok.Data;

@Data
public class CustomOrderDto {
    private Integer id;
    private Status status;
    private String notes;
    private Integer productsId;
    private Integer productsCategoriesId;
    private Integer measurementProfilesId;
    private Integer measurementProfilesCustomersId;
    private Integer measurementProfilesCustomersUsersId;
}
