package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.Controller.PerguntaController;
import com.orbitech.npvet.DTO.*;
import com.orbitech.npvet.Entity.*;
import com.orbitech.npvet.Repository.PerguntaRepository;
import com.orbitech.npvet.Service.PerguntaService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PerguntaControllerTest {

    @MockBean
    private PerguntaRepository perguntaRepository;
    @Autowired
    private PerguntaController perguntaController;
    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private ModelMapper modelMapper;


    Pergunta pergunta = new Pergunta();
    PerguntaDTO perguntaDTO = new PerguntaDTO();
    List<Pergunta> perguntaList = new ArrayList<>();
    List<PerguntaDTO> perguntaDTOList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        perguntaDTO.setId(1L);
        perguntaDTO.setEnunciado("Enunciado");
        perguntaDTOList.add(perguntaDTO);

        pergunta.setId(1L);
        pergunta.setEnunciado("Enunciado");
        perguntaList.add(pergunta);

        when(perguntaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(pergunta));
        when(perguntaRepository.findAll()).thenReturn(perguntaList);
        when(perguntaRepository.save(Mockito.any(Pergunta.class))).thenReturn(pergunta);

    }

    @Test
    void getByIdTest() {
        ResponseEntity<PerguntaDTO> response = perguntaController.getById(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(perguntaDTO);
    }
    @Test
    void getAllTest(){
        ResponseEntity<List<PerguntaDTO>>response = perguntaController.getAll();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(perguntaDTOList);
    }

    @Test
    void createTest(){
        ResponseEntity<PerguntaDTO> response = perguntaController.create(perguntaDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(perguntaDTO);
    }

    @Test
    void updateTest(){
        ResponseEntity<PerguntaDTO> response = perguntaController.update(1L, perguntaDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(perguntaDTO);
    }

    @Test
    void deleteTest(){
        ResponseEntity<String> response = perguntaController.delete(1L);
        assertEquals(HttpStatus.OK,response.getStatusCode());}

}
