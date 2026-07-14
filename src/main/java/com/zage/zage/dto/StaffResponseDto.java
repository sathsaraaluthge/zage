package com.zage.zage.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StaffResponseDto {

    private Long id;
    private String staffname;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean status;
    private LocalDateTime createdAt;

}