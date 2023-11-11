package com.orbitech.npvet.DTOTest;

import com.orbitech.npvet.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AnamneseDTOTest {

    AnamneseDTO anamnese = new AnamneseDTO();
    AnimalDTO animal = new AnimalDTO();
    TutorDTO tutor = new TutorDTO();
    UsuarioDTO veterinario = new UsuarioDTO();
    AnamneseHistoricoDTO anamneseHistorico = new AnamneseHistoricoDTO();
    List<AnamneseHistoricoDTO> anamneseHistoricos = new ArrayList<>();
    AnamnesePerguntaDTO anamnesePergunta = new AnamnesePerguntaDTO();
    List<AnamnesePerguntaDTO> anamnesePerguntas = new ArrayList<>();
    @BeforeEach
    void setUp(){

        animal.setId(1L);
        animal.setNome("Buddy");

        tutor.setId(1L);
        tutor.setCpf("123");

        veterinario.setId(1L);
        veterinario.setNome("Dr. Smith");

        anamneseHistorico.setId(1L);
//        anamneseHistorico.setAnamnese(anamnese);
        anamneseHistorico.setProgressoMedico("Medical Progress Sample One.");

        anamneseHistoricos.add(anamneseHistorico);

        anamnesePergunta.setId(1L);
//        anamnesePergunta.setAnamneseDTO(anamnese);
//        anamnesePergunta.setPerguntaDTO(new PerguntaDTO());

        anamnesePerguntas.add(anamnesePergunta);

        anamnese.setId(1L);
        anamnese.setAnimalDTO(animal);
        anamnese.setTutorDTO(tutor);
        anamnese.setVeterinarioDTO(veterinario);
        anamnese.setHistoricoProgressoMedico(anamneseHistoricos);

    }

    @Test
    void testAllArgsConstructor() {
        AnamneseDTO anamneseWithArgs = new AnamneseDTO(animal, tutor, veterinario, "queixaPrincipal",
                anamneseHistoricos, "alimentacao", "contactantes",
                "ambiente", "vacinacao", "vermifugacao",
                "sistemaRespiratorio", "sistemaCardiovascular",
                "sistemaUrinario", "sistemaReprodutor", "sistemaLocomotor",
                "sistemaNeurologico", "pele", "olhos",
                "ouvidos", anamnesePerguntas);
        assertNotNull(anamneseWithArgs);
        assertEquals(animal, anamneseWithArgs.getAnimalDTO());
        assertEquals(tutor, anamneseWithArgs.getTutorDTO());
        assertEquals(veterinario, anamneseWithArgs.getVeterinarioDTO());
        assertEquals("queixaPrincipal", anamneseWithArgs.getQueixaPrincipal());
    }

}
