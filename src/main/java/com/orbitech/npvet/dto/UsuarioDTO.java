package com.orbitech.npvet.dto;

import com.orbitech.npvet.entity.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO extends AbstractEntityDTO {

    @NotNull(message = "Você precisa preencher um nome.")
    @Size(max = 100, message = "Quantidade de caracteres excedida.")
    private String nome;

    @NotNull(message = "Você precisa preencher um CPF.")
    @Size(max = 11, message = "Quantidade de caracteres excedida.") //TODO: Adicionar a Annotation @CPF futuramente.
    private String cpf;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Você precisar definir o tipo de Usuário entre: SECRETARIA, ADMINISTRADOR ou MEDICO.")
    private TipoUsuario tipoUsuario;

    @NotNull(message = "Você precisa definir um nome de usuário.")
    @Size(max = 30, message = "Quantidade de caracteres excedida.")
    private String username;

    @NotNull(message = "Você precisa definir uma senha.")
    @Size(max = 20, message = "Quantidade de caracteres excedida.")
    private String senha;
}
