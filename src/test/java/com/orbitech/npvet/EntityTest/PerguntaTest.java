package com.orbitech.npvet.EntityTest;

import com.orbitech.npvet.entity.AnamnesePergunta;
import com.orbitech.npvet.entity.Pergunta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PerguntaTest {

    private Pergunta pergunta;

    List<AnamnesePergunta> anamnesePerguntas = new ArrayList<>();


    @BeforeEach
    void setUp() {
        pergunta = new Pergunta();
        pergunta.setEnunciado("Test Enunciado");


        AnamnesePergunta anamnese1 = new AnamnesePergunta();
        AnamnesePergunta anamnese2 = new AnamnesePergunta();

        anamnesePerguntas.add(anamnese1);
        anamnesePerguntas.add(anamnese2);

    }

    @Test
    void testSetAndGetEnunciado() {
        String enunciado = "Test Enunciado";
        pergunta.setEnunciado(enunciado);
        assertEquals(enunciado, pergunta.getEnunciado());
    }

    @Test
    void testAnamnesePerguntas() {
        pergunta.setAnamnesePerguntas(anamnesePerguntas);
        assertEquals(anamnesePerguntas, pergunta.getAnamnesePerguntas());
    }

    @Test
    void testNoArgsConstructor() {
        assertNotNull(pergunta);
    }

    @Test
    void testAllArgsConstructor() {
        Pergunta perguntaWithArgs = new Pergunta(anamnesePerguntas,"Test Enunciado");
        assertNotNull(perguntaWithArgs);
        assertEquals("Test Enunciado", perguntaWithArgs.getEnunciado());
    }


}
