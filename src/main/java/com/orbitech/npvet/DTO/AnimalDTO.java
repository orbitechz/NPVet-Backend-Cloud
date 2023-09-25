package com.orbitech.npvet.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orbitech.npvet.Entity.Sexo;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Entity.Vacina;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AnimalDTO extends AbstractEntityDTO{

    @NotNull(message = "É obrigatorio informar um nome")
    @NotBlank(message = "É obrigatorio informar o nome")
    private String nome;

    @NotNull(message = "É obrigatorio informar a especie")
    @NotBlank(message = "É obrigatorio informar a especie")
    private String especie;

    @NotNull(message = "É obrigatorio informar a raca")
    @NotBlank(message = "É obrigatorio informar a raca")
    private String raca;

    @NotNull(message = "É obrigatorio informar o sexo")
    private Sexo sexo;

    @NotNull(message = "É obrigatorio informar a idade")
    private Integer idade;

    @NotNull(message = "É obrigatorio informar o peso")
    private Double peso;

    @NotNull(message = "É obrigatorio informar o tipo de pelagem")
    @NotBlank(message = "É obrigatorio informar o tipo de pelagem")
    private String pelagem;

    @NotNull(message = "É obrigatorio informar a procedencia")
    @NotBlank(message = "É obrigatorio informar a procedencia")
    private String procedencia;

    @NotNull(message = "É obrigatorio informar o id do tutor")
    private TutorDTO tutor_id;

    @JsonIgnoreProperties("animal")
    private List<Vacina> vacinas;
}
