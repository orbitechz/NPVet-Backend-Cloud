package com.orbitech.npvet.EntityTest;

import com.orbitech.npvet.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AnimalTest {

    private Animal animal = new Animal();
    private Tutor tutor = new Tutor();


    @BeforeEach
    void setUp(){
        List<Anamnese> anamnese = new ArrayList<>();
        List<Consulta> consulta = new ArrayList<>();
        List<Vacina> vacina = new ArrayList<>();


        tutor.setId(2L);
        tutor.setNome("Alice");
        tutor.setCpf("123");


        animal.setId(1L);
        animal.setNome("nome");
        animal.setRaca("raca");
        animal.setEspecie("especie");
        animal.setIdade(11);
        animal.setPelagem("pelagem");
        animal.setProcedencia("procedencia");
        animal.setPeso(10.50);
        animal.setTutorId(tutor);
        animal.setVacinas(vacina);
        animal.setConsulta(consulta);
        animal.setAnamneses(anamnese);
        animal.setSexo(Sexo.MACHO);

    }

    @Test
    void animalIdTest(){assertEquals(1L, animal.getId());}

    @Test
    void animalNomeTest(){assertEquals("nome", animal.getNome());}

    @Test
    void animalRacaTest(){assertEquals("raca", animal.getRaca());}

    @Test
    void animalEspecieTest(){assertEquals("especie", animal.getEspecie());}

    @Test
    void animalIdadeTest(){assertEquals(11, animal.getIdade());}

    @Test
    void animalPelagemTest(){assertEquals("pelagem", animal.getPelagem());}

    @Test
    void animalProcedenciaTest(){assertEquals("procedencia", animal.getProcedencia());}

    @Test
    void animalPesoTest(){assertEquals(10.50, animal.getPeso());}

    @Test
    void animalTutorTest(){assertEquals(tutor, animal.getTutorId());}

    @Test
    void animalAllArgsConstructor(){
        List<Anamnese> anamnese = new ArrayList<>();
        List<Consulta> consulta = new ArrayList<>();
        List<Vacina> vacina = new ArrayList<>();

        Animal animalAllArgs = new Animal("nome", "especie", "raca", Sexo.MACHO, 11, 10.50, "pelagem", "procedencia", tutor, anamnese, consulta, vacina);
        assertThat(animalAllArgs).usingRecursiveComparison().ignoringFields("id").isEqualTo(animal);


    }

}
