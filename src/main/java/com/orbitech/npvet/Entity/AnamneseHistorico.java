package com.orbitech.npvet.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "anamnese_historico",schema = "public")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnamneseHistorico extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "anamnese_id", nullable = false)
    private Anamnese anamnese;

    @Column(name = "progresso_medico")
    private String progressoMedico;

    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;

    @PrePersist
    private void prePersist() {
        this.dataAtualizacao = LocalDate.now();
    }

}
