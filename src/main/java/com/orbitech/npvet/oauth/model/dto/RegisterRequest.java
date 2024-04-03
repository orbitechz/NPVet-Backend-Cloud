package com.orbitech.npvet.oauth.model.dto;

import com.orbitech.npvet.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nome;
    private String username;
    private String password;
    private Role role;
}