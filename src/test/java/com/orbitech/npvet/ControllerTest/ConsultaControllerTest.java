package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.controller.ConsultaController;
import com.orbitech.npvet.dto.*;
import com.orbitech.npvet.entity.*;
import com.orbitech.npvet.repository.ConsultaRepository;
import com.orbitech.npvet.service.ConsultaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class ConsultaControllerTest {

    @MockBean
    private ConsultaRepository repository;
    @Autowired
    private ConsultaController controller;
    @Autowired
    private ConsultaService service;

    private Consulta consultaEntidade = new Consulta();
    private ConsultaDTO consultaDTO = new ConsultaDTO();
    private List<Consulta> consultaList = new ArrayList<>();
    private List<ConsultaDTO>consultaDTOList = new ArrayList<>();
    private AnimalDTO animalDTO = new AnimalDTO();
    private TutorDTO tutorDTO = new TutorDTO();
    // private AnamneseDTO anamneseDTO = new AnamneseDTO();
    private List<ExameFisicoDTO>exameFisicoDTOList = new ArrayList<>();
    private UsuarioDTO veterinarios = new UsuarioDTO();
    private Animal animal = new Animal();
    private Tutor tutor = new Tutor();
    //private Anamnese anamnese = new Anamnese();
    private List<ExameFisico>exameFisicos = new ArrayList<>();
    private Usuario veterinariosEntidade = new Usuario();
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        exameFisicoDTOList.add(new ExameFisicoDTO());

        consultaDTO.setId(1L);
        consultaDTO.setAnimal(animalDTO);
        consultaDTO.setTutor(tutorDTO);
        //consultaDTO.setAnamnese(anamneseDTO);
        consultaDTO.setData(LocalDateTime.of(2023, Month.SEPTEMBER,24,0,0));
        consultaDTO.setStatus(Status.EM_ANDAMENTO);
        consultaDTO.setExamesFisicos(exameFisicoDTOList);
        consultaDTO.setVeterinario(veterinarios);
        consultaDTOList.add(consultaDTO);


        exameFisicos.add(new ExameFisico());

        consultaEntidade.setId(1L);
        consultaEntidade.setAnimal(animal);
        consultaEntidade.setTutor(tutor);
        //consultaEntidade.setAnamnese(anamnese);
        consultaEntidade.setData(LocalDateTime.of(2023, Month.SEPTEMBER,24,0,0));
        consultaEntidade.setStatus(Status.EM_ANDAMENTO);
        consultaEntidade.setExamesFisicos(exameFisicos);
        consultaEntidade.setVeterinario(veterinariosEntidade);
        consultaList.add(consultaEntidade);

        //when(repository.findById(1L)).thenReturn(Optional.of(consultaEntidade));
        when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(consultaEntidade));
        when(repository.findAll()).thenReturn(consultaList);
        when(repository.save(Mockito.any(Consulta.class))).thenReturn(consultaEntidade);

        when(repository.findConsultaByAnimalId(Mockito.anyLong())).thenReturn(consultaList);
        when(repository.findConsultaByAnimalName(Mockito.anyString())).thenReturn(consultaList);
        when(repository.findConsultaByVeterinarioNome(Mockito.anyString())).thenReturn(consultaList);
        when(repository.findConsultaByVeterinarioId(Mockito.anyLong())).thenReturn(consultaList);
        when(repository.findConsultaByAnamneseId(Mockito.anyLong())).thenReturn(consultaList);

        when(repository.findConsultasByStatus(Status.EM_ANDAMENTO)).thenReturn(consultaList);
        when(repository.findConsultasByStatus(Status.CONCLUIDA)).thenReturn(consultaList);
        when(repository.findConsultasByStatus(Status.AGENDADA)).thenReturn(consultaList);
        when(repository.findConsultasByStatus(Status.CANCELADA)).thenReturn(consultaList);
    }

    @Test
    void consultaFindById() throws Exception {
        ResponseEntity<ConsultaDTO> response = controller.getById(1L);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(consultaDTO);

    }
    @Test
    void consultaGetAllTest(){
        ResponseEntity<List<ConsultaDTO>>response = controller.getAll();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(consultaDTOList);
    }

    @Test
    void consultaPostTest(){
        ResponseEntity<ConsultaDTO>response = controller.create(consultaDTO);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(consultaDTO);
    }
    @Test
    void consultaPutTest(){
        ResponseEntity<ConsultaDTO>response = controller.update(1L,consultaDTO);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(consultaDTO);
    }
    @Test
    void consultaDeleteTest(){
        ResponseEntity<String>response = controller.delete(1L);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(repository,times(1)).deleteById(1L);
    }

    @Test
    void getConsultaByAnimalIdTest(){
        Long id = 1L;
        ResponseEntity<List<ConsultaDTO>>response = controller.getAnimalById(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(consultaDTOList);
    }

    @Test
    void getAnimalByNameTest(){
        ResponseEntity<List<ConsultaDTO>>response = controller.getAnimalByName("nome");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(consultaDTOList);
    }

    @Test
    void getVeterinarioByNameTest(){
        ResponseEntity<List<ConsultaDTO>>response = controller.getVeterinarioByNome("nome");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(consultaDTOList);
    }

    @Test
    void getConsultaByVeterinarioIdTest(){
        Long id = 1L;
        ResponseEntity<List<ConsultaDTO>>response = controller.getVeterinarioById(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(consultaDTOList);
    }

    @Test
    void getConsultaByAnamneseIdTest(){
        Long id = 1L;
        ResponseEntity<List<ConsultaDTO>>response = controller.getAnamneseById(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(consultaDTOList);
    }

    @Test
    void getConsultaEmAndamentoTest(){
        ResponseEntity<List<ConsultaDTO>>response = controller.getConsultasEmAndamento();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody().get(0)).usingRecursiveComparison().isEqualTo(consultaDTO);
    }

    @Test
    void getConsultaConcluida(){
        ResponseEntity<List<ConsultaDTO>>response = controller.getConsultasConcluida();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody().get(0)).usingRecursiveComparison().isEqualTo(consultaDTO);
    }

    @Test
    void getConsultaCanceladaTest(){
        ResponseEntity<List<ConsultaDTO>>response = controller.getConsultasCancelada();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody().get(0)).usingRecursiveComparison().isEqualTo(consultaDTO);
    }

    @Test
    void getConsultaAgendadaTest(){
        ResponseEntity<List<ConsultaDTO>>response = controller.getConsultasAgendadas();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody().get(0)).usingRecursiveComparison().isEqualTo(consultaDTO);
    }
}