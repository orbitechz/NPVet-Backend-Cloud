package com.orbitech.npvet.DTO;

import com.orbitech.npvet.Entity.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO extends AbstractEntityDTO {
    @NotNull(message = "Você precisa selecionar um paciente.")
    private AnimalDTO animal;
    @NotNull(message = "Você precisa selecionar um tutor.") //TODO: Decidir se deixamos o id do tutor na consulta, ou apenas na anamnese.
    private TutorDTO tutor;
    private AnamneseDTO anamnese;
    private List<ExameFisicoDTO>examesFisicos;
    @NotNull(message = "Informe a data.")
    private LocalDateTime data;
    @NotNull (message = "Você precisar informar o status.")
    @Size(max = 15)
    private Status status;
    @NotNull(message = "Você precisa informar o Médico Veterinário!")
    private UsuarioDTO veterinario;
}
