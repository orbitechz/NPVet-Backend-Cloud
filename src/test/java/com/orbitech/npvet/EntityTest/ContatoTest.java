package com.orbitech.npvet.EntityTest;

import com.orbitech.npvet.entity.Anamnese;
import com.orbitech.npvet.entity.Contato;
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
class ContatoTest {
    private Contato contato = new Contato();
    private List<Tutor> tutores = new ArrayList<>();


    @BeforeEach
    void setup(){
        tutores.add(new Tutor());
        contato.setId(1L);
        contato.setTelefone("4599999999");
        contato.setTutores(tutores);
    }
    @Test
    void contatoGetIdTest(){
        assertEquals(1L, contato.getId());
    }
    @Test
    void contatoGetTelefoneTest(){
        assertEquals("4599999999", contato.getTelefone());
    }
    @Test
    void contatoGetTutoresTest(){
        assertThat(contato.getTutores()).usingRecursiveComparison().isEqualTo(tutores);
    }
    @Test
    void contatoAllArgsTest(){
        Contato tutorAllArgs = new Contato("4599999999", tutores);
        assertThat(tutorAllArgs).usingRecursiveComparison().ignoringFields("id").isEqualTo(contato);
    }
}
