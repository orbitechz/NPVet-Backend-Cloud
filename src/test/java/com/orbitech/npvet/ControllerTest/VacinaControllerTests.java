package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.controller.VacinaController;
import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.VacinaDTO;
import com.orbitech.npvet.entity.Animal;
import com.orbitech.npvet.entity.Vacina;
import com.orbitech.npvet.repository.AnimalRepository;
import com.orbitech.npvet.repository.VacinaRepository;
import com.orbitech.npvet.service.VacinaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VacinaControllerTests {
    @Autowired
    private VacinaController controller;
    @Autowired
    private VacinaService service;
    @MockBean
    private VacinaRepository repository;
    @MockBean
    private AnimalRepository animalRepository;

    Vacina vacinaEntity = new Vacina();
    VacinaDTO vacinaDTO = new VacinaDTO();
    Animal animalEntity = new Animal();
    AnimalDTO animalDTO = new AnimalDTO();
    List<Vacina> vacinaEntityList = new ArrayList<>();
    List<VacinaDTO> vacinaDTOList = new ArrayList<>();

    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);

        animalEntity.setId(1L);
        animalDTO.setId(1L);

        vacinaDTO.setId(1L);
        vacinaDTO.setNome("Vacina");
        vacinaDTO.setDescricao("Descricao");
        vacinaDTO.setAnimal(animalDTO);
        vacinaDTOList.add(vacinaDTO);

        vacinaEntity.setId(1L);
        vacinaEntity.setNome("Vacina");
        vacinaEntity.setDescricao("Descricao");
        vacinaEntity.setAnimal(animalEntity);
        vacinaEntityList.add(vacinaEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(vacinaEntity));
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animalEntity));
        when(repository.findAllByAnimalId(Mockito.any(Long.class))).thenReturn(vacinaEntityList);
        when(repository.findAllByNomeLike(Mockito.any(String.class))).thenReturn(vacinaEntityList);
        when(repository.save(Mockito.any(Vacina.class))).thenReturn(vacinaEntity);
    }

    @Test
    void vacinaGetByIdTest() {
        ResponseEntity<VacinaDTO> controllerResponse = controller.getById(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(vacinaDTO);
    }
    @Test
    void vacinaGetByNomeTest() {
        ResponseEntity<List<VacinaDTO>> controllerResponse = controller.getByNome("Vacina");
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(vacinaDTOList);
    }

    @Test
    void vacinaGetByAnimalTest() {
        ResponseEntity<List<VacinaDTO>> controllerResponse = controller.getByAnimal(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(vacinaDTOList);
    }
    @Test
    void vacinaCreateTest() {
        vacinaDTO.setId(null);
        ResponseEntity<VacinaDTO> controllerResponse = controller.create(vacinaDTO);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().ignoringFields("id").isEqualTo(vacinaDTO);
    }
    @Test
    void vacinaPutTest() {
        ResponseEntity<VacinaDTO> controllerResponse = controller.update(1L, vacinaDTO);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(vacinaDTO);
    }

    @Test
    void vacinaDeleteTest() {
        ResponseEntity<String> controllerResponse = controller.delete(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals("Vacina 1 deletada com sucesso!", controllerResponse.getBody());
    }
}
