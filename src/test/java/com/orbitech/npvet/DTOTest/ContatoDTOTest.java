package com.orbitech.npvet.DTOTest;

import com.orbitech.npvet.dto.ContatoDTO;
import com.orbitech.npvet.dto.TutorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ContatoDTOTest {
    private ContatoDTO contato = new ContatoDTO();
    private List<TutorDTO> tutores = new ArrayList<>();
    @BeforeEach
    void setup(){
        tutores.add(new TutorDTO());
        contato.setId(1L);
        contato.setTelefone("4599999999");
        contato.setTutores(tutores);
    }
    @Test
    void contatoDtoGetIdTest(){
        assertEquals(1L, contato.getId());
    }
    @Test
    void contatoDtoGetTelefoneTest(){
        assertEquals("4599999999", contato.getTelefone());
    }
    @Test
    void contatoDtoGetTutoresTest(){
        assertThat(contato.getTutores()).usingRecursiveComparison().isEqualTo(tutores);
    }
    @Test
    void contatoDtoAllArgsTest(){
        ContatoDTO tutorAllArgs = new ContatoDTO("4599999999", tutores);
        assertThat(tutorAllArgs).usingRecursiveComparison().ignoringFields("id").isEqualTo(contato);
    }
}
