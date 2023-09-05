package com.orbitech.npvet.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO extends AbstractEntityDTO {
    @NotNull(message = "O nome do tutor deve ser informado!")
    @Length(min = 3, max = 50, message = "O nome do tutor deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotNull(message = "O CPF do tutor deve ser informado!")
    @CPF(message = "O CPF informado é inválido!")
    private String cpf;

    @NotNull(message = "O RG do tutor deve ser informado!")
    @Length(max = 20, message = "O RG deve ter no máximo 20 caracteres")
    @NotBlank(message = "O RG foi informado vazio!")
    private String rg;

    @Email(message = "O e-mail informado é inválido!")
    @Length(max = 100, message = "O e-mail deve ter apenas até 100 caracteres")
    private String email;
    private List<ContatoDTO> telefones;
    private List<EnderecoDTO> enderecos;
}
