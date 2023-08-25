package com.orbitech.npvet.entity;

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
    @OneToOne @NotNull(message = "Você precisa selecionar um tutor.")
    @Column(nullable = false)
    private Tutor tutor;
    @OneToMany
    private List<Anamnese>anamnese;
    private LocalDateTime data;
    private String status;
    private Veterinario veterinario;

}
