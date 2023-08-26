package com.orbitech.npvet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "perguntas",schema = "public")
@Getter @Setter
public class Pergunta extends AbstractEntity{

    @OneToMany(mappedBy = "pergunta")
    @JsonIgnoreProperties("pergunta")
    private List<AnamnesePergunta> anamnesePerguntas = new ArrayList<>();

    @Column(nullable = false,unique = true)
    private String enunciado;

}
