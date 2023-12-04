package com.orbitech.npvet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @ManyToOne
    @JoinColumn(nullable = false)
    private Tutor tutor;
    @OneToOne
    @JoinColumn
    private Anamnese anamnese;
    @OneToMany(mappedBy = "consulta")
    private List<ExameFisico> examesFisicos = new ArrayList<>();

    @Column(nullable = false, name = "data")
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario veterinario;
}
