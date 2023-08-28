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
public class Consulta extends AbstractEntity {
    @ManyToOne
    @JoinColumn(nullable = false)
    private Animal animal;
    @ManyToOne @NotNull(message = "Você precisa selecionar um tutor.")
    @JoinColumn(nullable = false)
    private Tutor tutor;
    @OneToOne
    private Anamnese anamnese;
    @OneToMany
    @JoinColumn(nullable = false) //TODO: Após a aprovação do Cléber, revisar essa regra de negócio.
    private List<ExameFisico>examesFisicos;

    @Column(nullable = false, name = "data")
    private LocalDateTime data;
    @NotNull (message = "Você precisar informar o status.")
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany @NotNull(message = "Você precisa informar o Médico Veterinário!")
    @JoinColumn(nullable = false)
    private List<Usuario> veterinario;
}
