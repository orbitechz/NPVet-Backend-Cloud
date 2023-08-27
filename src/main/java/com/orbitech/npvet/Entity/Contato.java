package com.orbitech.npvet.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contato",schema = "public")
public class Contato extends AbstractEntity{
    @Column(nullable = false)
    private String telefone;

    @ManyToMany(mappedBy = "telefones")
    private List<Tutor> tutores;
}
