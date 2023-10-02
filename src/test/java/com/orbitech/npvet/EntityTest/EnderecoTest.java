package com.orbitech.npvet.EntityTest;

import com.orbitech.npvet.entity.Endereco;
import com.orbitech.npvet.entity.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EnderecoTest {
    private Endereco endereco = new Endereco();
    private List<Tutor> tutores = new ArrayList<>();
    @BeforeEach
    void setup(){
        tutores.add(new Tutor());
        endereco.setId(1L);
        endereco.setLogradouro("Logradouro");
        endereco.setCidade("Cidade");
        endereco.setEstado("Estado");
        endereco.setPais("Pais");
        endereco.setNumero("123");
        endereco.setCep("85851010");
        endereco.setComplemento("Complemento");
        endereco.setResidentes(tutores);

    }
    @Test
    void enderecoDtoGetIdTest(){
        assertEquals(1L, endereco.getId());
    }
    @Test
    void enderecoDtoGetLogradouroTest(){
        assertEquals("Logradouro", endereco.getLogradouro());
    }
    @Test
    void enderecoDtoGetCidadeTest(){
        assertEquals("Cidade", endereco.getCidade());
    }
    @Test
    void enderecoDtoGetEstadoTest(){
        assertEquals("Estado", endereco.getEstado());
    }
    @Test
    void enderecoDtoGetPaisTest(){
        assertEquals("Pais", endereco.getPais());
    }
    @Test
    void enderecoDtoGetNumeroTest(){
        assertEquals("123", endereco.getNumero());
    }
    @Test
    void enderecoDtoGetCepTest(){
        assertEquals("85851010", endereco.getCep());
    }
    @Test
    void enderecoDtoGetComplementoTest(){
        assertEquals("Complemento", endereco.getComplemento());
    }
    @Test
    void enderecoDtoGetResidentesTest(){
        assertThat(endereco.getResidentes()).usingRecursiveComparison().isEqualTo(tutores);
    }
    @Test
    void enderecoDtoAllArgsTest(){
        Endereco enderecoAllArgs = new Endereco("Logradouro", "Cidade", "Estado", "Pais", "123", "85851010", "Complemento", tutores);
        assertThat(enderecoAllArgs).usingRecursiveComparison().ignoringFields("id").isEqualTo(endereco);
    }
}
