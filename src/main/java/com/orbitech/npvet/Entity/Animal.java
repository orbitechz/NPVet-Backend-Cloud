package com.orbitech.npvet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @OneToMany(mappedBy = "animal")
    @JsonIgnoreProperties("animal")
    private List<Anamnese> anamneses = new ArrayList<>();

}
