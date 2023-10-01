package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.controller.AnimalController;
import com.orbitech.npvet.controller.ExameFisicoController;
import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.ExameFisicoDTO;
import com.orbitech.npvet.entity.Animal;
import com.orbitech.npvet.entity.ExameFisico;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.AnimalRepository;
import com.orbitech.npvet.repository.ExameFisicoRepository;
import com.orbitech.npvet.service.ExameFisicoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExameFisicoControllerTest {

    @MockBean
    private ExameFisicoRepository repository;

    @MockBean
    private AnimalRepository animalRepository;

    @Autowired
    private ExameFisicoService service;

    @Autowired
    private ExameFisicoController controller;

    @MockBean
    private ModelMapper modelMapper;

    private ExameFisico exameFisico = new ExameFisico();
    private ExameFisicoDTO exameFisicoDTO = new ExameFisicoDTO();
    private Animal animal = new Animal();
    private AnimalDTO animalDTO = new AnimalDTO();

    @BeforeEach
    void setUp(){
        animal.setId(1L);
        animal.setNome("toto");
        animal.setRaca("Cachorro");
        animal.setEspecie("Cachorro");

        animalDTO.setId(1L);
        animalDTO.setNome("toto");
        animalDTO.setRaca("Cachorro");
        animalDTO.setEspecie("Cachorro");

        exameFisico.setAnimal(animal);
        exameFisico.setNivelConsciencia("Acordado");
        exameFisicoDTO.setAnimal(animalDTO);
        exameFisicoDTO.setNivelConsciencia("Acordado");

        List<ExameFisico> exameFisicoList = new ArrayList<>();
        exameFisicoList.add(exameFisico);

        when(repository.findById(1L)).thenReturn(Optional.of(exameFisico));
        when(repository.findAllByNomeLike(animal.getNome())).thenReturn(exameFisicoList);
        when(repository.findAllById(animal.getId())).thenReturn(exameFisicoList);
        when(repository.findAll()).thenReturn(exameFisicoList);

        when(repository.save(Mockito.any(ExameFisico.class))).thenReturn(exameFisico);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
    }

    @Test
    void getByIdTest(){
        ResponseEntity<ExameFisicoDTO> response = controller.getById(1L);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllTest(){
        ResponseEntity<List<ExameFisicoDTO>> response = controller.getAll();
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ExameFisicoDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
    }

    @Test
    void getAllByNomeAnimal(){
        ResponseEntity<List<ExameFisicoDTO>> response = controller.getByAnimalNome("toto");
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ExameFisicoDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
    }

    @Test
    void getAllByIdAnimal(){
        ResponseEntity<List<ExameFisicoDTO>> response = controller.getByAnimalId(1L);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ExameFisicoDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
    }

    @Test
    void createTest(){
        ResponseEntity<ExameFisicoDTO> response = controller.create(exameFisicoDTO);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(exameFisicoDTO).usingRecursiveComparison().isEqualTo(response.getBody());
    }

    @Test
    void updateTest(){
        exameFisico.setId(1L);
        exameFisicoDTO.setId(1L);

        ResponseEntity<ExameFisicoDTO> response = controller.update(1L, exameFisicoDTO);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(exameFisicoDTO).usingRecursiveComparison().isEqualTo(response.getBody());
    }

    @Test
    void deleteTest(){
        ResponseEntity<String>response = controller.delete(1L);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }



}
