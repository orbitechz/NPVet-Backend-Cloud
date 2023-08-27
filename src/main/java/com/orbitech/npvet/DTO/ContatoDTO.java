package com.orbitech.npvet.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbitech.npvet.Entity.Tutor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO extends AbstractEntityDTO{
    @NotNull(message = "O telefone deve ser informado!")
    @NotBlank(message = "O telefone foi informado vazio!")
    private String telefone;
    @JsonIgnore // Para evitar a auto-referencia e um JSON infinito
    private List<TutorDTO> tutores;
}
