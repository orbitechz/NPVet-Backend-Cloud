package com.orbitech.npvet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioAuthDTO {
    private String id;
    private String clientId;
    private String username;
    private String password;
    private String grantType;

}
