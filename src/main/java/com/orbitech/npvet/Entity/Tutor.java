package com.orbitech.npvet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tutor", schema = "public")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tutor extends AbstractEntity {
    @Column(nullable = false, length = 50)
    private String nome;

    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(unique = true, length = 20)
    private String rg;

    @Column(length = 100)
    private String email;

    @OneToMany(mappedBy = "tutor")
    @JsonIgnoreProperties("tutor")
    private List<Anamnese> anamneses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tutor_contato",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "contato_id"))
    private List <Contato> telefones;

    @ManyToMany
    @JoinTable(
            name = "tutor_endereco",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private List<Endereco> enderecos;
}
