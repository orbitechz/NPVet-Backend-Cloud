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
class TutorTest {
    private Tutor tutor = new Tutor();
    private List<Endereco> enderecos = new ArrayList<>();
    private List<Contato> contatos = new ArrayList<>();
    private List<Anamnese> anamneses = new ArrayList<>();
    @BeforeEach
    void setup(){
        tutor.setId(1L);
        tutor.setNome("Nome");
        tutor.setCpf("446.460.100-62");
        tutor.setRg("11.011.455-9");
        tutor.setEmail("email@email.com");
        tutor.setAnamneses(anamneses);
        tutor.setEnderecos(enderecos);
        tutor.setTelefones(contatos);
    }

    @Test
    void tutorGetIdTest(){
        assertEquals(1L, tutor.getId());
    }
    @Test
    void tutorGetNomeTest(){
        assertEquals("Nome", tutor.getNome());
    }
    @Test
    void tutorGetCpfTest(){
        assertEquals("446.460.100-62", tutor.getCpf());
    }
    @Test
    void tutorGetRgTest(){
        assertEquals("11.011.455-9", tutor.getRg());
    }
    @Test
    void tutorGetEmailTest(){
        assertEquals("email@email.com", tutor.getEmail());
    }
    @Test
    void tutorGetEnderecosTest(){
        assertThat(tutor.getEnderecos()).usingRecursiveComparison().isEqualTo(enderecos);
    }
    @Test
    void tutorGetAnamnesesTest(){
        assertThat(tutor.getAnamneses()).usingRecursiveComparison().isEqualTo(anamneses);
    }
    @Test
    void tutorGetTelefonesTest() {
        assertThat(tutor.getTelefones()).usingRecursiveComparison().isEqualTo(contatos);
    }
//    @Test
//    void tutorAllArgsTest(){
//        Tutor tutorAllArgs = new Tutor("Nome", "446.460.100-62", "11.011.455-9", "email@email.com", anamneses, contatos, enderecos);
//        assertThat(tutorAllArgs).usingRecursiveComparison().ignoringFields("id").isEqualTo(tutor);
//    }

}
