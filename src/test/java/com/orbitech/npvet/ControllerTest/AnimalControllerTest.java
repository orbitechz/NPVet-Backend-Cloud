package com.orbitech.npvet.ControllerTest;


import com.orbitech.npvet.Controller.AnimalController;
import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Entity.Animal;
import com.orbitech.npvet.Entity.Sexo;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Repository.AnimalRepository;
import com.orbitech.npvet.Service.AnimalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class AnimalControllerTest {

    @Autowired
    AnimalRepository repository;

    @Autowired
    AnimalService service;

    @MockBean
    AnimalController controller;

    private static final AnimalDTO animalDTO = new AnimalDTO("toto", "Cachorro", "Cachorro", Sexo.MACHO, 10, 10.50, "baixa", "duvidosa", new TutorDTO());
    private static final Animal animal = new Animal("toto", "Cachorro", "Cachorro", Sexo.MACHO, 10, 10.50, "baixa", "duvidosa", new Tutor());

    @BeforeEach
    void SetUP(){

        List<Animal> animalList = new ArrayList<>();
        animalList.add(animal);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(animal));
        Mockito.when(repository.findAll()).thenReturn(animalList);
        Mockito.when(repository.findAllByEspecieLike("Cachorro")).thenReturn(animalList);
        Mockito.when(repository.findAllByRacaLike("Cachorro")).thenReturn(animalList);
        Mockito.when(repository.findAllByNomeLike("toto")).thenReturn(animalList);

        Mockito.when(service.toAnimalDTO(animal)).thenReturn(animalDTO);
        Mockito.when(service.toAnimal(animalDTO)).thenReturn(animal);


    }

    @Test
    void getByIdTest(){
        ResponseEntity<AnimalDTO> response = controller.getById(1L);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(animalDTO, response.getBody());
    }

    @Test
    void getAllTest(){
        ResponseEntity<List<AnimalDTO>> response = controller.getAll();
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
        Assertions.assertEquals(animalDTO, responseBody.get(1));

    }

    @Test
    void getByNome(){
        ResponseEntity<List<AnimalDTO>> response = controller.getByNome("toto");
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
        Assertions.assertEquals(animalDTO, responseBody.get(1));
    }

    @Test
    void getByRaca(){
        ResponseEntity<List<AnimalDTO>> response = controller.getByRaca("Cachorro");
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
        Assertions.assertEquals(animalDTO, responseBody.get(1));
    }

    @Test
    void getByEspecie(){
        ResponseEntity<List<AnimalDTO>> response = controller.getByEspecie("Cachorro");
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
        Assertions.assertEquals(animalDTO, responseBody.get(1));
    }







}
