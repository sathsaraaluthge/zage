package com.zage.zage.dto;

import lombok.Data;

@Data
public class StaffRequestDto {

    private String staffname;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
}