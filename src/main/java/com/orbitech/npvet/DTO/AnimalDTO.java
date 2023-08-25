package com.orbitech.npvet.DTO;

import com.orbitech.npvet.entity.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    @NotNull(message = "É obrigatorio informar um nome")
    @NotBlank(message = "É obrigatorio informar o nome")
    private String nome;

    @NotNull(message = "É obrigatorio informar a especie")
    @NotBlank(message = "É obrigatorio informar a especie")
    private String especie;

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
    private Integer tutor_id;
}
