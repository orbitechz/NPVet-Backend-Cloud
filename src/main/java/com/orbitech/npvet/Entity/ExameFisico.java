package com.orbitech.npvet.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "exame_fisico", schema = "public")
public class ExameFisico extends AbstractEntity{

    @Column(nullable = true)
    private String postura;

    @Column(name ="nivel_consciencia", nullable = false)
    private String nivelConsciencia;

    @Column(name = "temperatura_retal", nullable = true)
    private Double temperaturaRetal;

    @Column(name = "frequencia_respiratoria", nullable = true)
    private String frequenciaRespiratoria;

    @Column(name = "frequencia_cardiaca", nullable = true)
    private String frequenciaCardiaca;

    @Column(name = "tempo_preenchimento_capilar", nullable = true)
    private LocalTime tempoPreenchimentoCapilar;

    @Column(nullable = true)
    private Double pulso;

    @Column(nullable = true)
    private String hidratacao;

    @Column(name = "linf_submand", nullable = true)
    private String linfSubmand;

    @Column(name = "linf_preescapulares", nullable = true)
    private String linf_preEscapulares;

    @Column(name = "linf_popliteos", nullable = true)
    private String linfPopliteos;

    @Column(name = "linf_Inguinais", nullable = true)
    private String linfInguinais;

    @Column(name = "mucosa_ocular", nullable = true)
    private String mucosaOcular;

    @Column(name = "mucosa_oral", nullable = true)
    private String mucosaOral;

    @Column(name = "mucosa_peniana", nullable = true)
    private String mucosaPeniana;

    @Column(name = "mucosa_anal", nullable = true)
    private String mucosaAnal;

    @Column()
    private Integer animal;

}
