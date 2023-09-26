package com.orbitech.npvet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exame_fisico", schema = "public")
public class ExameFisico extends AbstractEntity{

    @Column(nullable = true, length = 255)
    private String postura;

    @Column(name ="nivel_consciencia", nullable = false, length = 100)
    private String nivelConsciencia;

    @Column(name = "temperatura_retal", nullable = true)
    private Double temperaturaRetal;

    @Column(name = "frequencia_respiratoria", nullable = true)
    private Double frequenciaRespiratoria;

    @Column(name = "frequencia_cardiaca", nullable = true)
    private Double frequenciaCardiaca;

    @Column(name = "tempo_preenchimento_capilar", nullable = true)
    private LocalTime tempoPreenchimentoCapilar;

    @Column(nullable = true)
    private Double pulso;

    @Column(nullable = true, length = 255)
    private String hidratacao;

    @Column(name = "linf_submand", nullable = true, length = 255)
    private String linfSubmand;

    @Column(name = "linf_preescapulares", nullable = true, length = 255)
    private String linf_preEscapulares;

    @Column(name = "linf_popliteos", nullable = true, length = 255)
    private String linfPopliteos;

    @Column(name = "linf_Inguinais", nullable = true, length = 255)
    private String linfInguinais;

    @Column(name = "mucosa_ocular", nullable = true, length = 255)
    private String mucosaOcular;

    @Column(name = "mucosa_oral", nullable = true, length = 255)
    private String mucosaOral;

    @Column(name = "mucosa_peniana", nullable = true, length = 255)
    private String mucosaPeniana;

    @Column(name = "mucosa_anal", nullable = true, length = 255)
    private String mucosaAnal;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(nullable = true) //Oie
    private Consulta consulta;
}
