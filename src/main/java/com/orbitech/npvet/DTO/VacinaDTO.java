package com.orbitech.npvet.DTO;

import com.orbitech.npvet.Entity.Animal;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class VacinaDTO extends AbstractEntityDTO {
    @NotNull(message = "O nome da vacina deve ser informado!")
    @Length(min = 2, max = 50, message = "O nome da vacina deve ter entre 2 e 50 caractéres!")
    private String nome;
    @NotNull(message = "A data da aplicação da vacina deve ser informada!")
    private LocalDate dataAplicacao;
    private LocalDate dataRetorno;
    private String descricao;
    private Animal animal;
}
