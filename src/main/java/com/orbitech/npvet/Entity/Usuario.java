package com.orbitech.npvet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", schema = "public")
public class Usuario extends  AbstractEntity{

    @Column(nullable = false, length = 100, name = "nome")
    private String nome;

    @Column(unique = true, nullable = false, length = 11, name = "cpf") //TODO: Adicionar a Annotation @CPF futuramente.
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(unique = true, nullable = false, length = 30, name = "username")
    private String username;

    @Column(name = "senha", nullable = false, length = 20)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private List<Anamnese> anamneses = new ArrayList<>();
}
