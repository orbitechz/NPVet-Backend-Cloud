package com.orbitech.npvet.DTO;

import com.orbitech.npvet.Entity.Anamnese;
import com.orbitech.npvet.Entity.Animal;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Entity.Usuario;

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
    private List<AnamneseDTO> anamnese;
    @NotNull(message = "Informe a data.")
    private LocalDateTime data;
    @NotNull (message = "Você precisar informar o status.") //TODO: Verificar se isso não é um Enum
    @Size(max = 15)
    private String status;
    @NotNull(message = "Você precisa informar o Médico Veterinário!")
    private UsuarioDTO veterinario;
}
