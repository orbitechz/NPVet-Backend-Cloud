package com.orbitech.npvet.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class AnamneseHistoricoDTO extends AbstractEntityDTO {
    @NotBlank(message = "O progresso médico não pode estar em branco.")
    @Size(max = 255, message = "O progresso médico deve ter no máximo 255 caracteres.")
    private String progressoMedico;
    private LocalDate dataAtualizacao;
}
