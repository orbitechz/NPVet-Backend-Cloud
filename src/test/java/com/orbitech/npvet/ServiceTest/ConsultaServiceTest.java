package com.orbitech.npvet.ServiceTest;

import com.orbitech.npvet.Controller.ConsultaController;
import com.orbitech.npvet.DTO.*;
import com.orbitech.npvet.Entity.*;
import com.orbitech.npvet.Repository.ConsultaRepository;
import com.orbitech.npvet.Service.ConsultaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ConsultaServiceTest {
    @Mock
    private ConsultaRepository repository;
    @Mock
    private ConsultaController controller;
    @InjectMocks
    private ConsultaService service;

    private Consulta consultaEntidade = new Consulta();
    private ConsultaDTO consultaDTO = new ConsultaDTO();
    private List<Consulta> consultaList = new ArrayList<>();
    private List<ConsultaDTO>consultaDTOList = new ArrayList<>();
    private AnimalDTO animalDTO = new AnimalDTO();
    private TutorDTO tutorDTO = new TutorDTO();
    private List<ExameFisicoDTO>exameFisicoDTOList = new ArrayList<>();
    private UsuarioDTO veterinarios = new UsuarioDTO();
    private Animal animal = new Animal();
    private Tutor tutor = new Tutor();
    private List<ExameFisico>exameFisicos = new ArrayList<>();
    private Usuario veterinariosEntidade = new Usuario();
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        exameFisicoDTOList.add(new ExameFisicoDTO());

        consultaDTO.setId(1L);
        consultaDTO.setAnimal(animalDTO);
        consultaDTO.setTutor(tutorDTO);
        consultaDTO.setData(LocalDateTime.of(2023, Month.SEPTEMBER,24,0,0));
        consultaDTO.setStatus(Status.EM_ANDAMENTO);
        consultaDTO.setExamesFisicos(exameFisicoDTOList);
        consultaDTO.setVeterinario(veterinarios);
        consultaDTOList.add(consultaDTO);


        exameFisicos.add(new ExameFisico());

        consultaEntidade.setId(1L);
        consultaEntidade.setAnimal(animal);
        consultaEntidade.setTutor(tutor);
        consultaEntidade.setData(LocalDateTime.of(2023, Month.SEPTEMBER,24,0,0));
        consultaEntidade.setStatus(Status.EM_ANDAMENTO);
        consultaEntidade.setExamesFisicos(exameFisicos);
        consultaEntidade.setVeterinario(veterinariosEntidade);
        consultaList.add(consultaEntidade);

        when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(consultaEntidade));
        when(repository.findAll()).thenReturn(consultaList);
        when(repository.save(Mockito.any(Consulta.class))).thenReturn(consultaEntidade);

    }

    @Test
    void consultaFindById() throws Exception {
       ConsultaDTO result = service.getById(1L);
       assertNotNull(result);
       verify(repository,times(1)).findById(1L);
    }
    @Test
    void consultaGetAllTest(){
        List <ConsultaDTO> result = service.getAll();
        assertNotNull(result);
        verify(repository,times(1)).findAll();
    }

    @Test
    void consultaPostTest(){

    }
    @Test
    void consultaPutTest(){

    }
    @Test
    void consultaDeleteTest(){

    }





}
