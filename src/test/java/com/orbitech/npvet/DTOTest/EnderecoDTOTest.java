package com.orbitech.npvet.DTOTest;

import com.orbitech.npvet.dto.EnderecoDTO;
import com.orbitech.npvet.dto.TutorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EnderecoDTOTest {
    private EnderecoDTO enderecoDTO = new EnderecoDTO();
    private List<TutorDTO> tutores = new ArrayList<>();
    @BeforeEach
    void setup(){
        tutores.add(new TutorDTO());
        enderecoDTO.setId(1L);
        enderecoDTO.setLogradouro("Logradouro");
        enderecoDTO.setCidade("Cidade");
        enderecoDTO.setEstado("Estado");
        enderecoDTO.setPais("Pais");
        enderecoDTO.setNumero("123");
        enderecoDTO.setCep("85851010");
        enderecoDTO.setComplemento("Complemento");
        enderecoDTO.setResidentes(tutores);

    }
    @Test
    void enderecoDtoGetIdTest(){
        assertEquals(1L, enderecoDTO.getId());
    }
    @Test
    void enderecoDtoGetLogradouroTest(){
        assertEquals("Logradouro", enderecoDTO.getLogradouro());
    }
    @Test
    void enderecoDtoGetCidadeTest(){
        assertEquals("Cidade", enderecoDTO.getCidade());
    }
    @Test
    void enderecoDtoGetEstadoTest(){
        assertEquals("Estado", enderecoDTO.getEstado());
    }
    @Test
    void enderecoDtoGetPaisTest(){
        assertEquals("Pais", enderecoDTO.getPais());
    }
    @Test
    void enderecoDtoGetNumeroTest(){
        assertEquals("123", enderecoDTO.getNumero());
    }
    @Test
    void enderecoDtoGetCepTest(){
        assertEquals("85851010", enderecoDTO.getCep());
    }
    @Test
    void enderecoDtoGetComplementoTest(){
        assertEquals("Complemento", enderecoDTO.getComplemento());
    }
    @Test
    void enderecoDtoGetResidentesTest(){
        assertThat(enderecoDTO.getResidentes()).usingRecursiveComparison().isEqualTo(tutores);
    }
    @Test
    void enderecoDtoAllArgsTest(){
        EnderecoDTO enderecoAllArgs = new EnderecoDTO("Logradouro", "Cidade", "Estado", "Pais", "123", "85851010", "Complemento", tutores);
        assertThat(enderecoAllArgs).usingRecursiveComparison().ignoringFields("id").isEqualTo(enderecoDTO);
    }
}
