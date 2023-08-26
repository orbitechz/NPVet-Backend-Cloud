package com.orbitech.npvet.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnamneseDTO extends AbstractEntityDTO {

    @NotNull(message = "O animal deve ser informado!")
    private AnimalDTO animalDTO;

    @NotNull(message = "O tutor deve ser informado!")
    private TutorDTO tutorDTO;

    @NotNull(message = "O médico deve ser informado!")
    private UsuarioDTO usuarioDTO;

    private String queixaPrincipal;

    private String progressoMedico;

    private String alimentacao;

    private String contactantes;

    private String ambiente;

    private String vacinacao;

    private String vermifugacao;

    private String sistemaRespiratorio;

    private String sistemaCardiovascular;

    private String sistemaUrinario;

    private String sistemaReprodutor;

    private String sistemaLocomotor;

    private String sistemaNeurologico;

    private String pele;

    private String olhos;

    private String ouvidos;


}
