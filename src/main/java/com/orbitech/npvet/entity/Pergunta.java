package com.orbitech.npvet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "perguntas",schema = "public")
@Getter @Setter
@NoArgsConstructor
public class Pergunta extends AbstractEntity{

    @Column(nullable = false,unique = true)
    private String enunciado;

    public Pergunta(String enunciado) {
        this.enunciado = enunciado;
    }
}
