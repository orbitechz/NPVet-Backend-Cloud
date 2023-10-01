package com.orbitech.npvet.ServiceTest;

import com.orbitech.npvet.controller.AnimalController;
import com.orbitech.npvet.controller.PerguntaController;
import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.PerguntaDTO;
import com.orbitech.npvet.dto.TutorDTO;
import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.Animal;
import com.orbitech.npvet.entity.Tutor;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.AnimalRepository;
import com.orbitech.npvet.repository.PerguntaRepository;
import com.orbitech.npvet.repository.TutorRepository;
import com.orbitech.npvet.service.AnimalService;
import com.orbitech.npvet.service.PerguntaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class AnimalServiceTest {

    @Mock
    private AnimalRepository repository;

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private AnimalService service;

    @Autowired
    private AnimalController controller;

    @Autowired
    private ModelMapper modelMapper;


    private AnimalDTO animalDTO = new AnimalDTO();
    private Animal animal = new Animal();

    private TutorDTO tutorDTO = new TutorDTO();

    private Tutor tutor = new Tutor();

    @BeforeEach
    void setUp(){
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
        /*
        when(modelMapper.map(animal, AnimalDTO.class)).thenReturn(animalDTO);
        when(modelMapper.map(animalDTO, Animal.class)).thenReturn(animal);
        */
    }

    @Test
    void getByidTest(){
        AnimalDTO result = service.getById(1L);
        assertNotNull(result);
        verify(repository,times(1)).findById(1L);
    }

    @Test
    void getAllTest(){
        List<AnimalDTO> result = service.getAll();
        assertNotNull(result);
        verify(repository,times(1)).findAll();
    }

    @Test
    void getAllByNome(){
        List<AnimalDTO> result = service.getAllByNome(animal.getNome());
        assertNotNull(result);
        verify(repository,times(1)).findAllByNomeLike(animal.getNome());
    }

    @Test
    void getAllByRaca(){
        List<AnimalDTO> result = service.getAllByRaca(animal.getRaca());
        assertNotNull(result);
        verify(repository,times(1)).findAllByRacaLike(animal.getRaca());
    }

    @Test
    void getAllByEspecie(){
        List<AnimalDTO> result = service.getAllByEspecie(animal.getEspecie());
        assertNotNull(result);
        verify(repository,times(1)).findAllByEspecieLike(animal.getEspecie());
    }

    @Test
    void createTest(){
        AnimalDTO result = service.create(animalDTO);
        assertNotNull(result);
        verify(repository,times(1)).save(Mockito.any(Animal.class));
    }

    @Test
    void updateTest(){
        animalDTO.setId(1L);
        animal.setId(1L);
        AnimalDTO result = service.update(1L, animalDTO);
        assertNotNull(result);
        verify(repository,times(1)).save(Mockito.any(Animal.class));
    }

    @Test
    void deleteTest(){
        service.delete(1L);
        assertNotNull(repository);
        verify(repository,times(1)).deleteById(1L);
    }




}
