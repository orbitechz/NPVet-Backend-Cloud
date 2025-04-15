package com.orbitech.npvet.EntityTest;

import com.orbitech.npvet.entity.AnamneseHistorico;
import com.orbitech.npvet.entity.Anamnese;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AnamneseHistoricoTest {

//    @Test
//    void testAllArgsConstructor() {
//        Anamnese anamnese = new Anamnese();
//        anamnese.setId(1L);
//
//        LocalDate dataAtualizacao = LocalDate.now();
//        String progressoMedico = "Test Progresso Medico";
//
////        AnamneseHistorico anamneseHistorico = new AnamneseHistorico(anamnese, progressoMedico, dataAtualizacao);
//        assertNotNull(anamneseHistorico);
////        assertEquals(anamnese, anamneseHistorico.getAnamnese());
//        assertEquals(progressoMedico, anamneseHistorico.getProgressoMedico());
//        assertEquals(dataAtualizacao, anamneseHistorico.getDataAtualizacao());
//    }

//    @Test
//    void testAnamnese() {
//        Anamnese anamnese = new Anamnese();
//        anamnese.setId(1L);
//
//        AnamneseHistorico anamneseHistorico = new AnamneseHistorico();
//        anamneseHistorico.setAnamnese(anamnese);
//        assertEquals(anamnese, anamneseHistorico.getAnamnese());
//    }

    @Test
    void testProgressoMedico() {
        AnamneseHistorico anamneseHistorico = new AnamneseHistorico();
        anamneseHistorico.setProgressoMedico("Test Progresso Medico");
        assertEquals("Test Progresso Medico", anamneseHistorico.getProgressoMedico());
    }

    @Test
    void testDataAtualizacao() {
        AnamneseHistorico anamneseHistorico = new AnamneseHistorico();
        assertNull(anamneseHistorico.getDataAtualizacao());

        anamneseHistorico.prePersistDataAtualizacao();
        assertNotNull(anamneseHistorico.getDataAtualizacao());
        assertEquals(LocalDate.now(), anamneseHistorico.getDataAtualizacao());
    }
}
