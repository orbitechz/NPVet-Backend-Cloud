package com.orbitech.npvet.DTO;

import com.orbitech.npvet.entity.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO {

    @NotNull(message = "Você precisa preencher um nome.")
    private String nome;

    @NotNull(message = "Você precisa preencher um CPF.")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Você precisar definir o tipo de Usuário entre: SECRETARIA, ADMINISTRADOR ou MEDICO.")
    private TipoUsuario tipoUsuario;

    @NotNull(message = "Você precisa definir um nome de usuário.")
    private String username;

    @NotNull(message = "Você precisa definir uma senha.")
    private String senha;
}
