package com.orbitech.npvet.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String access_token;
    private String realm_acess;
    private String roles;
}