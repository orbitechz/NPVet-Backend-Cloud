package com.orbitech.npvet.EntityTest;


import com.orbitech.npvet.entity.Animal;
import com.orbitech.npvet.entity.Consulta;
import com.orbitech.npvet.entity.ExameFisico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.testng.Assert.assertEquals;

@SpringBootTest
class ExameFisicoTest {

    private final ExameFisico exameFisico = new ExameFisico();
    private final Animal animal = new Animal();
    private final Consulta consulta= new Consulta();
    @BeforeEach
    void setUp() {

        consulta.setId(1L);
        consulta.setAnimal(animal);


        animal.setId(1L);
        animal.setNome("toto");
        animal.setRaca("Cachorro");
        animal.setEspecie("Cachorro");

        exameFisico.setAnimal(animal);
        exameFisico.setNivelConsciencia("consiencia");
        exameFisico.setTemperaturaRetal(10.0);
        exameFisico.setFrequenciaCardiaca(10.0);
        exameFisico.setFrequenciaRespiratoria(10.0);
        exameFisico.setTempoPreenchimentoCapilar(LocalTime.parse("08:20:45"));
        exameFisico.setPulso(10.0);
        exameFisico.setHidratacao("hidratacao");
        exameFisico.setLinfSubmand("linfSub");
        exameFisico.setLinfPreEscapulares("linfPre");
        exameFisico.setLinfPopliteos("linfPop");
        exameFisico.setLinfInguinais("linfIng");
        exameFisico.setMucosaOcular("mucosaOcular");
        exameFisico.setMucosaOral("mucosaOral");
        exameFisico.setMucosaPeniana("mucosaPeniana");
        exameFisico.setMucosaAnal("mucosaAnal");
        exameFisico.setAnimal(animal);
        exameFisico.setConsulta(consulta);
        exameFisico.setPostura("postura");

    }

    @Test
    void testPostura(){assertEquals("postura", exameFisico.getPostura());}

    @Test
    void testNivelConsciencia(){assertEquals("consiencia", exameFisico.getNivelConsciencia());}

    @Test
    void testTemperaturaRetal(){assertEquals(10.0, exameFisico.getTemperaturaRetal());}

    @Test
    void testFrequenciaCardiaca(){assertEquals(10.0, exameFisico.getFrequenciaCardiaca());}

    @Test
    void testFrequenciaRespiratoria(){assertEquals(10.0, exameFisico.getFrequenciaRespiratoria());}

    @Test
    void testTempoPreenchimentoCapilar(){assertEquals(LocalTime.parse("08:20:45"), exameFisico.getTempoPreenchimentoCapilar());}

    @Test
    void testPulso(){assertEquals(10.0, exameFisico.getPulso());}

    @Test
    void testHidratacao(){assertEquals("hidratacao", exameFisico.getHidratacao());}

    @Test
    void testLinfSubmand(){assertEquals("linfSub", exameFisico.getLinfSubmand());}

    @Test
    void testLinfPreEscapulares() {
        assertEquals("linfPre", exameFisico.getLinfPreEscapulares());
    }

    @Test
    void testLinfPopliteos() {
        assertEquals("linfPop", exameFisico.getLinfPopliteos());
    }

    @Test
    void testLinfInguinais() {
        assertEquals("linfIng", exameFisico.getLinfInguinais());
    }

    @Test
    void testMucosaOcular() {
        assertEquals("mucosaOcular", exameFisico.getMucosaOcular());
    }

    @Test
    void testMucosaOral() {
        assertEquals("mucosaOral", exameFisico.getMucosaOral());
    }

    @Test
    void testMucosaPeniana() {
        assertEquals("mucosaPeniana", exameFisico.getMucosaPeniana());
    }

    @Test
    void testMucosaAnal() {
        assertEquals("mucosaAnal", exameFisico.getMucosaAnal());
    }

    @Test
    void testAnimal() {
        assertEquals(animal, exameFisico.getAnimal());
    }

    @Test
    void testConsulta() {
        assertEquals(consulta, exameFisico.getConsulta());
    }

}
