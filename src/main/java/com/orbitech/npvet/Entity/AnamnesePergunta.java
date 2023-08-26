package com.orbitech.npvet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "anamnese-pergunta",schema = "public")
@Getter
@Setter
public class AnamnesePergunta extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "id_anamnese")
    @JsonIgnoreProperties("anamnesePerguntas")
    private Anamnese anamnese;

    @ManyToOne
    @JoinColumn(name = "id_pergunta")
    private Pergunta pergunta;

    @Column(nullable = false)
    private String repsposta;

}
