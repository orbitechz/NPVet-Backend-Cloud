package com.orbitech.npvet.EntityTest;

import com.orbitech.npvet.entity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AnamneseTest {

    Anamnese anamnese = new Anamnese();
    Animal animal = new Animal();
    Tutor tutor = new Tutor();
    Usuario veterinario = new Usuario();
    AnamneseHistorico anamneseHistorico = new AnamneseHistorico();
    List<AnamneseHistorico> anamneseHistoricos = new ArrayList<>();
    AnamnesePergunta anamnesePergunta = new AnamnesePergunta();
    List<AnamnesePergunta> anamnesePerguntas = new ArrayList<>();
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
//        anamnesePergunta.setAnamnese(anamnese);
//        anamnesePergunta.setPergunta(new Pergunta());

        anamnesePerguntas.add(anamnesePergunta);

        anamnese.setId(1L);
        anamnese.setAnimal(animal);
        anamnese.setTutor(tutor);
        anamnese.setVeterinario(veterinario);
        anamnese.setHistoricoProgressoMedico(anamneseHistoricos);

    }

//    @Test
//    void testAllArgsConstructor() {
//        Anamnese anamneseWithArgs = new Anamnese(animal, tutor, veterinario, "queixaPrincipal",
//                anamneseHistoricos, "alimentacao", "contactantes",
//                "ambiente", "vacinacao", "vermifugacao",
//                "sistemaRespiratorio", "sistemaCardiovascular",
//                "sistemaUrinario", "sistemaReprodutor", "sistemaLocomotor",
//                "sistemaNeurologico", "pele", "olhos",
//                "ouvidos", anamnesePerguntas);
//        assertNotNull(anamneseWithArgs);
//        assertEquals(animal, anamneseWithArgs.getAnimal());
//        assertEquals(tutor, anamneseWithArgs.getTutor());
//        assertEquals(veterinario, anamneseWithArgs.getVeterinario());
//        assertEquals("queixaPrincipal", anamneseWithArgs.getQueixaPrincipal());
//    }

    @Test
    void testTutor() {
        Tutor newTutor = new Tutor();
        newTutor.setId(2L);
        newTutor.setCpf("456");
        anamnese.setTutor(newTutor);
        assertEquals(newTutor, anamnese.getTutor());
    }

    @Test
    void testVeterinario() {
        Usuario newVeterinario = new Usuario();
        newVeterinario.setId(2L);
        newVeterinario.setNome("Dr. Johnson");
        anamnese.setVeterinario(newVeterinario);
        assertEquals(newVeterinario, anamnese.getVeterinario());
    }

    @Test
    void testAnimal() {
        Animal newAnimal = new Animal();
        newAnimal.setId(2L);
        newAnimal.setNome("Fido");
        anamnese.setAnimal(newAnimal);
        assertEquals(newAnimal, anamnese.getAnimal());
    }

    @Test
    void testEqualsAndHashCode() {
        Anamnese anamnese1 = new Anamnese();
        anamnese1.setId(1L);
        Anamnese anamnese2 = new Anamnese();
        anamnese2.setId(1L);

        assertEquals(anamnese1, anamnese2);
        assertEquals(anamnese1.hashCode(), anamnese2.hashCode());

        Anamnese differentAnamnese = new Anamnese();
        differentAnamnese.setId(2L);
        assertNotEquals(null, anamnese1);
    }

    @Test
    void testHistoricoProgressoMedico() {
        List<AnamneseHistorico> historicoProgressoMedico = new ArrayList<>();
        AnamneseHistorico historico1 = new AnamneseHistorico();
        AnamneseHistorico historico2 = new AnamneseHistorico();
        historicoProgressoMedico.add(historico1);
        historicoProgressoMedico.add(historico2);
        anamnese.setHistoricoProgressoMedico(historicoProgressoMedico);
        assertEquals(historicoProgressoMedico, anamnese.getHistoricoProgressoMedico());
    }

    @Test
    void testAnamnesePerguntas() {
        List<AnamnesePergunta> anamnesePerguntas = new ArrayList<>();
        AnamnesePergunta pergunta1 = new AnamnesePergunta();
        AnamnesePergunta pergunta2 = new AnamnesePergunta();
        anamnesePerguntas.add(pergunta1);
        anamnesePerguntas.add(pergunta2);
        anamnese.setAnamnesePerguntas(anamnesePerguntas);
        assertEquals(anamnesePerguntas, anamnese.getAnamnesePerguntas());
    }

}
