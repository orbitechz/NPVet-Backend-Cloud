package com.orbitech.npvet.EntityTest;

import com.orbitech.npvet.entity.AnamnesePergunta;
import com.orbitech.npvet.entity.Anamnese;
import com.orbitech.npvet.entity.Pergunta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnamnesePerguntaTest {

    private AnamnesePergunta anamnesePergunta;

    @BeforeEach
    void setUp() {
        anamnesePergunta = new AnamnesePergunta();
        Anamnese anamnese = new Anamnese();
        Pergunta pergunta = new Pergunta();

        anamnese.setId(1L);
        pergunta.setId(1L);

//        anamnesePergunta.setAnamnese(anamnese);
//        anamnesePergunta.setPergunta(pergunta);
//        anamnesePergunta.setResposta("Test Resposta");
    }

//    @Test
//    void testAllArgsConstructor() {
//        Anamnese anamnese = new Anamnese();
//        Pergunta pergunta = new Pergunta();
//
//        anamnese.setId(1L);
//        pergunta.setId(1L);
//
//        AnamnesePergunta anamnesePerguntaWithArgs = new AnamnesePergunta(anamnese, pergunta, "Test Resposta");
//        assertNotNull(anamnesePerguntaWithArgs);
//        assertEquals(1L, anamnesePerguntaWithArgs.getAnamnese().getId());
//        assertEquals(1L, anamnesePerguntaWithArgs.getPergunta().getId());
//        assertEquals("Test Resposta", anamnesePerguntaWithArgs.getResposta());
//    }

//    @Test
//    void testAnamnese() {
//        assertNotNull(anamnesePergunta.getAnamnese());
//        assertEquals(1L, anamnesePergunta.getAnamnese().getId());
//    }

//    @Test
//    void testPergunta() {
//        assertNotNull(anamnesePergunta.getPergunta());
//        assertEquals(1L, anamnesePergunta.getPergunta().getId());
//    }

//    @Test
//    void testResposta() {
//        assertEquals("Test Resposta", anamnesePergunta.getResposta());
//    }
}
