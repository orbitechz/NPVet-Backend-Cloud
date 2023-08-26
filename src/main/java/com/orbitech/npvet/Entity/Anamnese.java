package com.orbitech.npvet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "anamneses", schema = "public")
@Getter @Setter
public class Anamnese extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "id_animal",nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "id_tutor",nullable = false)
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;

    @Column(name = "queixa-principal")
    private String queixaPrincipal;

    @Column(name = "progresso-medico")
    private String progressoMedico;

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
