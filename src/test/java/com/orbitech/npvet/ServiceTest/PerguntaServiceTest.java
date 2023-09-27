package com.orbitech.npvet.ServiceTest;

import com.orbitech.npvet.controller.PerguntaController;
import com.orbitech.npvet.dto.PerguntaDTO;
import com.orbitech.npvet.entity.Anamnese;
import com.orbitech.npvet.entity.Pergunta;
import com.orbitech.npvet.repository.ConsultaRepository;
import com.orbitech.npvet.repository.PerguntaRepository;
import com.orbitech.npvet.service.ConsultaService;
import com.orbitech.npvet.service.PerguntaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PerguntaServiceTest {
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

}
