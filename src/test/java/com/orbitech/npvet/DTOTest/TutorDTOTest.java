package com.orbitech.npvet.DTOTest;

import com.orbitech.npvet.dto.AnamneseDTO;
import com.orbitech.npvet.dto.ContatoDTO;
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
public class TutorDTOTest {
    private TutorDTO tutor = new TutorDTO();
    private List<AnamneseDTO> anamneseDTO = new ArrayList<>();
    private List<EnderecoDTO> enderecosDTO = new ArrayList<>();
    private List<ContatoDTO> contatosDTO = new ArrayList<>();

    @BeforeEach
    void setup(){
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();
        List<ContatoDTO> contatosDTO = new ArrayList<>();
        tutor.setId(1L);
        tutor.setNome("Nome");
        tutor.setCpf("446.460.100-62");
        tutor.setRg("11.011.455-9");
        tutor.setEmail("email@email.com");
        tutor.setAnamneses(anamneseDTO);
        tutor.setEnderecos(enderecosDTO);
        tutor.setTelefones(contatosDTO);
    }

    @Test
    void tutorDtoGetIdTest(){
        assertEquals(1L, tutor.getId());
    }
    @Test
    void tutorDtoGetNomeTest(){
        assertEquals("Nome", tutor.getNome());
    }
    @Test
    void tutorDtoGetCpfTest(){
        assertEquals("446.460.100-62", tutor.getCpf());
    }
    @Test
    void tutorDtoGetRgTest(){
        assertEquals("11.011.455-9", tutor.getRg());
    }
    @Test
    void tutorDtoGetEmailTest(){
        assertEquals("email@email.com", tutor.getEmail());
    }
    @Test
    void tutorDtoGetAnamnesesTest(){
        assertThat(tutor.getAnamneses()).usingRecursiveComparison().isEqualTo(anamneseDTO);
    }
    @Test
    void tutorDtoGetEnderecosTest(){
        List<EnderecoDTO> enderecos = new ArrayList<>();
        assertThat(tutor.getEnderecos()).usingRecursiveComparison().isEqualTo(enderecos);
    }
    @Test
    void tutorDtoGetTelefonesTest() {
        List<ContatoDTO> telefones = new ArrayList<>();
        assertThat(tutor.getTelefones()).usingRecursiveComparison().isEqualTo(telefones);
    }
    @Test
    void tutorDtoAllArgsTest(){
        TutorDTO tutorDTO = new TutorDTO("Nome", "446.460.100-62", "11.011.455-9", "email@email.com", anamneseDTO, contatosDTO, enderecosDTO);
        assertThat(tutorDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(tutor);
    }

}
