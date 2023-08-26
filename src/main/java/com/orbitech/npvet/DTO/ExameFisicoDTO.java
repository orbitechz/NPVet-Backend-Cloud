package com.orbitech.npvet.DTO;

import com.orbitech.npvet.Entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExameFisicoDTO extends AbstractEntity {

    private String postura;

    @NotNull(message = "O nivel de consciencia do animal é obrigatorio")
    private String nivelConsciencia;

    private Double temperaturaRetal;

    private String frequenciaRespiratoria;

    private String frequenciaCardiaca;

    private LocalTime tempoPreenchimentoCapilar;

    private Double pulso;

    private String hidratacao;

    private String linfSubmand;

    private String linf_preEscapulares;

    private String linfPopliteos;

    private String linfInguinais;

    private String mucosaOcular;

    private String mucosaOral;

    private String mucosaPeniana;

    private String mucosaAnal;

    @NotNull(message = "É obrigatorio informar o animal")
    private AnimalDTO animal;

}
