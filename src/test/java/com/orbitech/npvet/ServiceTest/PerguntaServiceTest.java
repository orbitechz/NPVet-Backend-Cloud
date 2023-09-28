package com.orbitech.npvet.ServiceTest;

import com.orbitech.npvet.controller.PerguntaController;
import com.orbitech.npvet.dto.PerguntaDTO;
import com.orbitech.npvet.entity.Pergunta;
import com.orbitech.npvet.repository.PerguntaRepository;
import com.orbitech.npvet.service.PerguntaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PerguntaServiceTest {
    @Mock
    private PerguntaRepository perguntaRepository;
    @Mock
    private PerguntaController perguntaController;
    @InjectMocks
    private PerguntaService perguntaService;
    @Autowired
    private ModelMapper modelMapper;
    Pergunta pergunta = new Pergunta();
    PerguntaDTO perguntaDTO = new PerguntaDTO();
    List<Pergunta> perguntaList = new ArrayList<>();
    List<PerguntaDTO> perguntaDTOList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        perguntaDTO.setId(1L);
        perguntaDTO.setEnunciado("Enunciado");
        perguntaDTOList.add(perguntaDTO);

        pergunta.setId(1L);
        pergunta.setEnunciado("Enunciado");
        perguntaList.add(pergunta);

        when(perguntaRepository.findById(1L)).thenReturn(Optional.of(pergunta));
        when(perguntaRepository.findAll()).thenReturn(perguntaList);
        when(perguntaRepository.save(Mockito.any(Pergunta.class))).thenReturn(pergunta);
    }

    @Test
    void testGetById(){
        PerguntaDTO result = perguntaService.getById(1L);
        assertNotNull(result);
        verify(perguntaRepository,times(1)).findById(1L);
    }

    @Test
    void testGetAll() {
        List<PerguntaDTO> result = perguntaService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllWithoutAnamneses() {
        when(perguntaRepository.findAll()).thenReturn(Collections.emptyList());
        List<PerguntaDTO> result = perguntaService.getAll();
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void createTest(){
        PerguntaDTO result = perguntaService.create(perguntaDTO);
        assertNotNull(result);
        verify(perguntaRepository,times(1)).save(Mockito.any(Pergunta.class));
    }

    @Test
    void updateTest(){
        PerguntaDTO result = perguntaService.update(1L,perguntaDTO);
        assertNotNull(result);
        verify(perguntaRepository,times(1)).save(Mockito.any(Pergunta.class));
    }

    @Test
    void testNonUniqueEnunciadoUpdate() {
        when(perguntaRepository.findByEnunciado("Enunciado")).thenReturn(new Pergunta());

        PerguntaDTO duplicatePerguntaDTO = new PerguntaDTO();
        duplicatePerguntaDTO.setEnunciado("DuplicateEnunciado");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            perguntaService.update(1L, duplicatePerguntaDTO);
        });

        assertEquals("O ID na URL não corresponde ao ID no corpo da requisição.", exception.getMessage());
    }


    @Test
    void deleteTest(){
        perguntaService.delete(1L);
        assertNotNull(perguntaRepository);
    }


}
