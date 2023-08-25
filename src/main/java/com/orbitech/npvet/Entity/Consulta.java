package com.orbitech.npvet.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consulta", schema = "public")
public class Consulta {
    @OneToOne @NotNull(message = "Você precisa selecionar um paciente.")
    @Column(nullable = false)
    private Animal animal;
    @OneToOne @NotNull(message = "Você precisa selecionar um tutor.") //TODO: Decidir se deixamos o id do tutor na consulta, ou apenas na anamnese.
    @Column(nullable = false)
    private Tutor tutor;
    @OneToMany
    private List<Anamnese>anamnese;
    @NotNull(message = "Informe a data.")
    @Column(nullable = false, name = "data")
    private LocalDateTime data;
    @NotNull (message = "Você precisar informar o status.")
    @Column(name="status", nullable = false, length = 15) //TODO: Verificar se isso não é um Enum.
    private String status;
    @OneToMany @NotNull(message = "Você precisa informar o Médico Veterinário!")
    @Column(nullable = false)
    private Usuario veterinario;
}
