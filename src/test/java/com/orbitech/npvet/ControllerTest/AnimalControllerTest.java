package com.orbitech.npvet.ControllerTest;


import com.orbitech.npvet.controller.AnimalController;
import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.TutorDTO;
import com.orbitech.npvet.entity.Animal;
import com.orbitech.npvet.entity.Tutor;
import com.orbitech.npvet.repository.AnimalRepository;
import com.orbitech.npvet.repository.TutorRepository;
import com.orbitech.npvet.service.AnimalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AnimalControllerTest {

    @MockBean
    private AnimalRepository repository;

    @MockBean
    private TutorRepository tutorRepository;

    @Autowired
    private AnimalService service;

    @Autowired
    private AnimalController controller;

    @MockBean
    private ModelMapper modelMapper;

    private final AnimalDTO animalDTO = new AnimalDTO();
    private final Animal animal = new Animal();

    private final TutorDTO tutorDTO = new TutorDTO();

    private final Tutor tutor = new Tutor();


    @BeforeEach
    void SetUP(){
        tutorDTO.setId(2L);
        tutorDTO.setNome("Alice");
        tutorDTO.setCpf("123");

        tutor.setId(2L);
        tutor.setNome("Alice");
        tutor.setCpf("123");

        animal.setNome("toto");
        animal.setRaca("Cachorro");
        animal.setEspecie("Cachorro");
        animal.setIdade(10);
        animal.setPelagem("baixa");
        animal.setProcedencia("Duvidosa");
        animal.setPeso(10.50);
        animal.setTutor_id(tutor);

        animalDTO.setNome("toto");
        animalDTO.setRaca("Cachorro");
        animalDTO.setEspecie("Cachorro");
        animalDTO.setIdade(10);
        animalDTO.setPelagem("baixa");
        animalDTO.setProcedencia("Duvidosa");
        animalDTO.setPeso(10.50);
        animalDTO.setTutor_id(tutorDTO);


        List<Animal> animalList = new ArrayList<>();
        animalList.add(animal);

        when(tutorRepository.findById(2L)).thenReturn(Optional.of(tutor));


        when(repository.findById(1L)).thenReturn(Optional.of(animal));
        when(repository.findAll()).thenReturn(animalList);
        when(repository.findAllByEspecieLike("Cachorro")).thenReturn(animalList);
        when(repository.findAllByRacaLike("Cachorro")).thenReturn(animalList);
        when(repository.findAllByNomeLike("toto")).thenReturn(animalList);

        when(repository.save(Mockito.any(Animal.class))).thenReturn(animal);

        when(modelMapper.map(animal, AnimalDTO.class)).thenReturn(animalDTO);
        when(modelMapper.map(animalDTO, Animal.class)).thenReturn(animal);



    }

    @Test
    void getByIdTest(){
        ResponseEntity<AnimalDTO> response = controller.getById(1L);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllTest(){
        ResponseEntity<List<AnimalDTO>> response = controller.getAll();
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
    }

    @Test
    void getByNome(){
        ResponseEntity<List<AnimalDTO>> response = controller.getByNome("toto");
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
    }

    @Test
    void getByRaca(){
        ResponseEntity<List<AnimalDTO>> response = controller.getByRaca("Cachorro");
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
    }

    @Test
    void getByEspecie(){
        ResponseEntity<List<AnimalDTO>> response = controller.getByEspecie("Cachorro");
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
    }

    @Test
    void createTest(){
        ResponseEntity<AnimalDTO> response = controller.create(animalDTO);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(animalDTO).usingRecursiveComparison().isEqualTo(response.getBody());
    }

    @Test
    void updateTest(){
        animalDTO.setId(1L);
        animal.setId(1L);
        ResponseEntity<AnimalDTO> response = controller.update(1L, animalDTO);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(animalDTO).usingRecursiveComparison().isEqualTo(response.getBody());
    }

    @Test
    void deleteTest(){
        ResponseEntity<String>response = controller.delete(1L);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}