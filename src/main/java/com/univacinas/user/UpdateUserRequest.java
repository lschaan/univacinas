package com.univacinas.user;

import lombok.Data;

@Data
public class UpdateUserRequest {

    private String username;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
