package com.pfms.gateway_server.model;

import lombok.Data;

@Data
public class UserDto {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String role;
}
