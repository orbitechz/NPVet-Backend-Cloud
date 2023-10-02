package com.orbitech.npvet.DTOTest;


import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.ConsultaDTO;
import com.orbitech.npvet.dto.ExameFisicoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.testng.Assert.assertEquals;

@SpringBootTest
class ExameFisicoDTOTest {

    private final ExameFisicoDTO exameFisicoDTO = new ExameFisicoDTO();
    private final AnimalDTO animal = new AnimalDTO();
    private final ConsultaDTO consultaDTO = new ConsultaDTO();

    @BeforeEach
    void setUp() {
        consultaDTO.setId(1L);
        consultaDTO.setAnimal(animal);

        animal.setId(1L);
        animal.setNome("toto");
        animal.setRaca("Cachorro");
        animal.setEspecie("Cachorro");

        exameFisicoDTO.setAnimal(animal);
        exameFisicoDTO.setNivelConsciencia("consiencia");
        exameFisicoDTO.setTemperaturaRetal(10.0);
        exameFisicoDTO.setFrequenciaCardiaca(10.0);
        exameFisicoDTO.setFrequenciaRespiratoria(10.0);
        exameFisicoDTO.setTempoPreenchimentoCapilar(LocalTime.parse("08:20:45"));
        exameFisicoDTO.setPulso(10.0);
        exameFisicoDTO.setHidratacao("hidratacao");
        exameFisicoDTO.setLinfSubmand("linfSub");
        exameFisicoDTO.setLinfPreEscapulares("linfPre");
        exameFisicoDTO.setLinfPopliteos("linfPop");
        exameFisicoDTO.setLinfInguinais("linfIng");
        exameFisicoDTO.setMucosaOcular("mucosaOcular");
        exameFisicoDTO.setMucosaOral("mucosaOral");
        exameFisicoDTO.setMucosaPeniana("mucosaPeniana");
        exameFisicoDTO.setMucosaAnal("mucosaAnal");
        exameFisicoDTO.setPostura("postura");
    }

    @Test
    void testPostura() {
        assertEquals("postura", exameFisicoDTO.getPostura());
    }

    @Test
    void testNivelConsciencia() {
        assertEquals("consiencia", exameFisicoDTO.getNivelConsciencia());
    }

    @Test
    void testTemperaturaRetal() {
        assertEquals(10.0, exameFisicoDTO.getTemperaturaRetal());
    }

    @Test
    void testFrequenciaCardiaca() {
        assertEquals(10.0, exameFisicoDTO.getFrequenciaCardiaca());
    }

    @Test
    void testFrequenciaRespiratoria() {
        assertEquals(10.0, exameFisicoDTO.getFrequenciaRespiratoria());
    }

    @Test
    void testTempoPreenchimentoCapilar() {
        assertEquals(LocalTime.parse("08:20:45"), exameFisicoDTO.getTempoPreenchimentoCapilar());
    }

    @Test
    void testPulso() {
        assertEquals(10.0, exameFisicoDTO.getPulso());
    }

    @Test
    void testHidratacao() {
        assertEquals("hidratacao", exameFisicoDTO.getHidratacao());
    }

    @Test
    void testLinfSubmand() {
        assertEquals("linfSub", exameFisicoDTO.getLinfSubmand());
    }

    @Test
    void testSetAnimal() {
        assertEquals(animal, exameFisicoDTO.getAnimal());
    }

    @Test
    void testSetPostura() {
        assertEquals("postura", exameFisicoDTO.getPostura());
    }
}
