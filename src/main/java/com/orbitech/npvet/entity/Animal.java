package com.orbitech.npvet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(nullable = false)
    private Tutor tutorId;

    @OneToMany(mappedBy = "animal")
    @JsonIgnoreProperties("animal")
    private List<Anamnese> anamneses = new ArrayList<>();

    @OneToMany(mappedBy = "animal")
    private List<Consulta> consulta;

    @OneToMany(mappedBy = "animal")
    @JsonIgnoreProperties("animal")
    private List<Vacina> vacinas;

}