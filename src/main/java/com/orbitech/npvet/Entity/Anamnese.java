package com.orbitech.npvet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "anamneses",schema = "public")
@Getter @Setter
public class Anamnese extends AbstractEntity{

    @Column(name = "queixa-principal",nullable = false)
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
