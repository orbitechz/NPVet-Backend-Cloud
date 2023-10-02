package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.controller.TutorController;
import com.orbitech.npvet.dto.ContatoDTO;
import com.orbitech.npvet.dto.EnderecoDTO;
import com.orbitech.npvet.dto.TutorDTO;
import com.orbitech.npvet.dto.VacinaDTO;
import com.orbitech.npvet.entity.Contato;
import com.orbitech.npvet.entity.Endereco;
import com.orbitech.npvet.entity.Tutor;
import com.orbitech.npvet.entity.Vacina;
import com.orbitech.npvet.repository.AnimalRepository;
import com.orbitech.npvet.repository.TutorRepository;
import com.orbitech.npvet.service.TutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TutorControllerTest {
    @Autowired
    private TutorController controller;
    @Autowired
    private TutorService service;
//    @MockBean
//    private TutorService mockService;
    @MockBean
    private TutorRepository repository;
    Tutor tutorEntity = new Tutor();
    TutorDTO tutorDTO = new TutorDTO();
    List<Tutor> tutorEntityList = new ArrayList<>();
    List<TutorDTO> tutorDTOList = new ArrayList<>();

    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();
        List<ContatoDTO> contatosDTO = new ArrayList<>();
        tutorDTO.setId(1L);
        tutorDTO.setNome("Nome");
        tutorDTO.setCpf("446.460.100-62");
        tutorDTO.setRg("11.011.455-9");
        tutorDTO.setEmail("email@email.com");
        tutorDTO.setEnderecos(enderecosDTO);
        tutorDTO.setTelefones(contatosDTO);
        tutorDTOList.add(tutorDTO);


        List<Endereco> enderecos = new ArrayList<>();
        List<Contato> contatos = new ArrayList<>();
        tutorEntity.setId(1L);
        tutorEntity.setNome("Nome");
        tutorEntity.setCpf("446.460.100-62");
        tutorEntity.setRg("11.011.455-9");
        tutorEntity.setEmail("email@email.com");
        tutorEntity.setEnderecos(enderecos);
        tutorEntity.setTelefones(contatos);
        tutorEntityList.add(tutorEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(tutorEntity));
        when(repository.findByCpf(Mockito.any(String.class))).thenReturn(tutorEntity);
        when(repository.findByRg(Mockito.any(String.class))).thenReturn(tutorEntity);
        when(repository.findAllByNomeLike(Mockito.any(String.class))).thenReturn(tutorEntityList);
        when(repository.save(Mockito.any(Tutor.class))).thenReturn(tutorEntity);
    }

    @Test
    void tutorGetByIdTest() {
        ResponseEntity<TutorDTO> controllerResponse = controller.getById(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(tutorDTO);
    }
    @Test
    void tutorGetByNomeTest() {
        ResponseEntity<List<TutorDTO>> controllerResponse = controller.getByNome("Nome");
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(tutorDTOList);
    }
    @Test
    void tutorGetByCpfTest() {
        ResponseEntity<TutorDTO> controllerResponse = controller.getByCpf("077.029.049-33");
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(tutorDTO);
    }

    @Test
    void tutorGetByRgTest() {
        ResponseEntity<TutorDTO> controllerResponse = controller.getByRg("11.011.455-9");
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(tutorDTO);
    }
    @Test
    void tutorCreateExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            controller.create(tutorDTO);
        });
    }
    @Test
    void tutorPutTest() {
        ResponseEntity<TutorDTO> controllerResponse = controller.update(1L, tutorDTO);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(tutorDTO);
    }

    @Test
    void tutorDeleteTest() {
        ResponseEntity<String> controllerResponse = controller.delete(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals("Tutor 1 desativado com sucesso!", controllerResponse.getBody());
    }

}
