package com.orbitech.npvet.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnamnesePerguntaDTO extends AbstractEntityDTO{

    @NotNull(message = "A anamnese deve ser informada!")
    private AnamneseDTO anamneseDTO;

    @NotNull(message = "A pergunta deve ser informada!")
    private PerguntaDTO perguntaDTO;

    @NotNull(message = "A resposta deve ser informada!")
    private String resposta;

}
