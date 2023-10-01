package com.orbitech.npvet.DTOTest;

import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.TutorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.testng.Assert.assertEquals;

@SpringBootTest
class AnimalDTOTest {

    private AnimalDTO animalDTO = new AnimalDTO();
    private TutorDTO tutorDTO = new TutorDTO();


    @BeforeEach
    void setUp(){
        tutorDTO.setId(2L);
        tutorDTO.setNome("Alice");
        tutorDTO.setCpf("123");


        animalDTO.setId(1L);
        animalDTO.setNome("toto");
        animalDTO.setRaca("Cachorro");
        animalDTO.setEspecie("Cachorro");
        animalDTO.setIdade(10);
        animalDTO.setPelagem("baixa");
        animalDTO.setProcedencia("Duvidosa");
        animalDTO.setPeso(10.50);
        animalDTO.setTutor_id(tutorDTO);

    }

    @Test
    void animalIdTest(){assertEquals(1L, animalDTO.getId());}

    @Test
    void animalNomeTest(){assertEquals("toto", animalDTO.getNome());}

    @Test
    void animalRacaTest(){assertEquals("Cachorro", animalDTO.getRaca());}

    @Test
    void animalEspecieTest(){assertEquals("Cachorro", animalDTO.getEspecie());}

    @Test
    void animalIdadeTest(){assertEquals(10, animalDTO.getIdade());}

    @Test
    void animalPelagemTest(){assertEquals("baixa", animalDTO.getPelagem());}

    @Test
    void animalProcedenciaTest(){assertEquals("Duvidosa", animalDTO.getProcedencia());}

    @Test
    void animalPesoTest(){assertEquals(10.50, animalDTO.getPeso());}

    @Test
    void animalTutorTest(){assertEquals(tutorDTO, animalDTO.getTutor_id());}


}
