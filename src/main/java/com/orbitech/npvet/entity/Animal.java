package com.orbitech.npvet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "animal", schema = "public")
public class Animal{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    private Sexo sexo;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private String pelagem;

    @Column(nullable = false)
    private String procedencia;

    @Column(nullable = false)
    private Integer tutor_id;

}
