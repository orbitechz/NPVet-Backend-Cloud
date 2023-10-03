package com.orbitech.npvet.ServiceTest;

import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.ExameFisicoDTO;
import com.orbitech.npvet.entity.Animal;
import com.orbitech.npvet.entity.ExameFisico;
import com.orbitech.npvet.repository.ExameFisicoRepository;
import com.orbitech.npvet.service.AnimalService;
import com.orbitech.npvet.service.ExameFisicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExameFisicoServiceTest {

    @Mock
    private ExameFisicoRepository repository;

    @Mock
    private AnimalService animalService;

    @InjectMocks
    private ExameFisicoService service;

    @Autowired
    private ModelMapper modelMapper;

    private final ExameFisico exameFisico = new ExameFisico();
    private final ExameFisicoDTO exameFisicoDTO = new ExameFisicoDTO();
    private final Animal animal = new Animal();
    private final AnimalDTO animalDTO = new AnimalDTO();

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

        when(animalService.getById(1L)).thenReturn(animalDTO);
    }

    @Test
    void getByidTest(){
        ExameFisicoDTO result = service.getById(1L);
        assertNotNull(result);
        verify(repository,times(1)).findById(1L);
    }

    @Test
    void getAllTest(){
        List<ExameFisicoDTO> result = service.getAll();
        assertNotNull(result);
        verify(repository,times(1)).findAll();
    }

    @Test
    void getAllByIdAnimal(){
        List<ExameFisicoDTO> result = service.getByIdAnimal(1L);
        assertNotNull(result);
        verify(repository,times(1)).findAllById(1L);

    }

    @Test
    void getAllByNomeAnimal(){
        List<ExameFisicoDTO> result = service.getByNomeAnimal("toto");
        assertNotNull(result);
        verify(repository,times(1)).findAllByNomeLike("toto");

    }

    @Test
    void createTest(){
        ExameFisicoDTO result = service.create(exameFisicoDTO);
        assertNotNull(result);
        verify(repository,times(1)).save(Mockito.any(ExameFisico.class));
    }

    @Test
    void updateTest(){
        exameFisicoDTO.setId(1L);
        exameFisico.setId(1L);

        ExameFisicoDTO result = service.update(1L, exameFisicoDTO);
        assertNotNull(result);
        verify(repository,times(1)).save(Mockito.any(ExameFisico.class));
    }

    @Test
    void deleteTest(){
        service.delete(1L);
        verify(repository,times(1)).findById(1L);
    }


}
