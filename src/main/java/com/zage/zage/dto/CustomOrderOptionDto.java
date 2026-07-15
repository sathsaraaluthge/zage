package com.zage.zage.dto;

import lombok.Data;

@Data
public class CustomOrderOptionDto {
    private Integer id;
    private Integer customOrdersId;
    private Integer customOrdersProductsId;
    private Integer customOrdersProductsCategoriesId;
    private Integer customOrdersMeasurementProfilesId;
    private Integer customOrdersMeasurementProfilesCustomersId;
    private Integer customOrdersMeasurementProfilesCustomersUsersId;
    private Integer customizationOptionsId;
    private Integer customizationValuesId;
    private Integer customizationValuesCustomizationOptionsId;
}
