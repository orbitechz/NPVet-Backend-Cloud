package com.orbitech.npvet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "vacina", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacina extends AbstractEntity{
    @Column(nullable = false, length = 50)
    private String nome;
    @Column(nullable = false, name = "data_aplicacao")
    private LocalDate dataAplicacao;
    @Column(name = "data_retorno")
    private LocalDate dataRetorno;
    private String descricao;
    @ManyToOne
    private Animal animal;
}
