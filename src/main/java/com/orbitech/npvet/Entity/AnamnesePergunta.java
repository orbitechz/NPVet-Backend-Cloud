package com.orbitech.npvet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "anamnese-pergunta",schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnamnesePergunta extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "id_anamnese",nullable = false)
    @JsonIgnoreProperties("anamnesePerguntas")
    private Anamnese anamnese;

    @ManyToOne
    @JoinColumn(name = "id_pergunta",nullable = false)
    private Pergunta pergunta;

    @Column(nullable = false)
    private String repsposta;

}
