package com.orbitech.npvet.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(unique = true, nullable = false, length = 20)
    private String rg;

    @Column(length = 100)
    private String email;
}
