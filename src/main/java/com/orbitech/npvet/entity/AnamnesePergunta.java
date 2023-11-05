package com.orbitech.npvet.entity;

import com.fasterxml.jackson.annotation.*;
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
    @JoinColumn(name = "id_anamnese")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true) // Serialize as the ID, not the full object
    @JsonIgnoreProperties("anamnesePerguntas")
    private Anamnese anamnese;

    @ManyToOne
    @JoinColumn(name = "id_pergunta",nullable = false)
    @JsonIgnoreProperties("anamnesePerguntas")
    private Pergunta pergunta;

    @Column(nullable = false)
    private String resposta;

}
