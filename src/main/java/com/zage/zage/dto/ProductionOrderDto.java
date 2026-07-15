package com.zage.zage.dto;

import com.zage.zage.enums.Stage;
import com.zage.zage.enums.Status;
import lombok.Data;

@Data
public class ProductionOrderDto {
    private Integer id;
    private Stage stage;
    private Status status;
    private Integer customOrdersId;
    private Integer customOrdersProductsId;
    private Integer customOrdersProductsCategoriesId;
    private Integer customOrdersMeasurementProfilesId;
    private Integer customOrdersMeasurementProfilesCustomersId;
    private Integer customOrdersMeasurementProfilesCustomersUsersId;
}
