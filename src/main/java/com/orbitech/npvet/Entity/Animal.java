package com.orbitech.npvet.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal", schema = "public")
public class Animal extends AbstractEntity{

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String especie;

    @Column(nullable = false, length = 100)
    private String raca;

    @Column(nullable = false)
    private Sexo sexo;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false, length = 255)
    private String pelagem;

    @Column(nullable = false, length = 255)
    private String procedencia;

    @Column(nullable = false)
    private Integer tutor_id;

}
