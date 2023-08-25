package com.orbitech.npvet.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", schema = "public")
public class Usuario {

    @Id @Column(unique = true, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Você precisa preencher um nome.")
    @Column(nullable = false, length = 100, name = "nome")
    private String nome;

    @NotNull(message = "Você precisa preencher um CPF.")
    @Column(unique = true, nullable = false, length = 11, name = "cpf") //TODO: Adicionar a Annotation @CPF futuramente.
    private String cpf;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Você precisar definir o tipo de Usuário entre: SECRETARIA, ADMINISTRADOR ou MEDICO.")
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @NotNull(message = "Você precisa definir um nome de usuário.")
    @Column(unique = true, nullable = false, length = 30, name = "username") //TODO: Consultar Giovani sobre username/password authentication
    private String username;

    @NotNull(message = "Você precisa definir uma senha.")
    @Column(name = "senha", nullable = false, length = 20)
    private String senha;
}
