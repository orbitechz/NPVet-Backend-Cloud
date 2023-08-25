package com.orbitech.npvet.DTO;

import com.orbitech.npvet.Entity.Anamnese;
import com.orbitech.npvet.Entity.Usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultaDTO {
    @NotNull(message = "Você precisa selecionar um paciente.")
    private Animal animal;
    @NotNull(message = "Você precisa selecionar um tutor.") //TODO: Decidir se deixamos o id do tutor na consulta, ou apenas na anamnese.
    private Tutor tutor;
    private List<Anamnese> anamnese;
    @NotNull(message = "Informe a data.")
    private LocalDateTime data;
    @NotNull (message = "Você precisar informar o status.") //TODO: Verificar se isso não é um Enum
    @Size(max = 15)
    private String status;
    @NotNull(message = "Você precisa informar o Médico Veterinário!")
    private Usuario veterinario;
}
