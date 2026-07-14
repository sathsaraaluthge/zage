package com.zage.zage.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String profileImage;
    private String notes;
    private String password;
}
