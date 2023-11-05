package com.orbitech.npvet.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class AnamneseHistoricoDTO extends AbstractEntityDTO {
    @NotBlank(message = "O progresso médico não pode estar em branco.")
    private String progressoMedico;
    private LocalDate dataAtualizacao;
}
