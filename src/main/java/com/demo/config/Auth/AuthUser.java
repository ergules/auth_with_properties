package com.demo.config.Auth;

import lombok.Data;

@Data
public class AuthUser {
    private String name;
    private String pass;
    private String[] roles;
}
