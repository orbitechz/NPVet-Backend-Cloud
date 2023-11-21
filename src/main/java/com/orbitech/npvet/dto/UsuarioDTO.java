package com.orbitech.npvet.dto;

import com.orbitech.npvet.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String id;
    @NotNull(message = "Você precisa preencher um nome.")
    @Size(max = 100, message = "Quantidade de caracteres excedida.")
    private String nome;

    @NotNull(message = "Você precisa preencher um CPF.")
    @CPF(message = "CPF inválido.")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Você precisar definir o tipo de Usuário entre: SECRETARIA, ADMINISTRADOR ou MEDICO.")
    private Role role;

    @NotNull(message = "Você precisa definir um nome de usuário.")
    @Size(max = 30, message = "Quantidade de caracteres excedida.")
    private String username;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }
    public void activate(){
        this.deletedAt = null;
    }
}
