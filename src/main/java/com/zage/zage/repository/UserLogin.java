package com.zage.zage.repository;

import lombok.Data;

@Data
public class UserLogin {
    private Long id;
    private Long userId;
    private String username;
    private String password;
    private String role;
}
