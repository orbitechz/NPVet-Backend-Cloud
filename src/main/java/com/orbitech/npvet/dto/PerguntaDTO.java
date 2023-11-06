package com.orbitech.npvet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PerguntaDTO extends AbstractEntityDTO{

    @NotNull(message = "O enunciado deve ser informado!")
    private String enunciado;
}
