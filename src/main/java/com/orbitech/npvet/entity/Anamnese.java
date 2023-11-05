package com.orbitech.npvet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "anamneses", schema = "public")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Anamnese extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "id_animal",nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "id_tutor",nullable = false)
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "id_veterinario",nullable = false)
    private Usuario veterinario;

    @Column(name = "queixa-principal",nullable = true)
    private String queixaPrincipal;

//    @OneToMany(mappedBy = "anamnese")
//    @JsonIgnoreProperties("anamnese")

    @OneToMany
    private List<AnamneseHistorico> historicoProgressoMedico = new ArrayList<>();
    
    private String alimentacao;

    private String contactantes;

    private String ambiente;

    private String vacinacao;

    private String vermifugacao;

    @Column(name = "sistema-respiratorio")
    private String sistemaRespiratorio;

    @Column(name = "sistema-cardiovascular")
    private String sistemaCardiovascular;

    @Column(name = "sistema-urinario")
    private String sistemaUrinario;

    @Column(name = "sistema-reprodutor")
    private String sistemaReprodutor;

    @Column(name = "sistema-locomotor")
    private String sistemaLocomotor;

    @Column(name = "sistema-neurologico")
    private String sistemaNeurologico;

    private String pele;

    private String olhos;

    private String ouvidos;

    @OneToMany(mappedBy = "anamnese")
    @JsonIgnoreProperties("anamnese")
    private List<AnamnesePergunta> anamnesePerguntas = new ArrayList<>();

}
