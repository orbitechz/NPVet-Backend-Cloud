package com.orbitech.npvet.ServiceTest;

import com.orbitech.npvet.controller.TutorController;
import com.orbitech.npvet.dto.ContatoDTO;
import com.orbitech.npvet.dto.EnderecoDTO;
import com.orbitech.npvet.dto.TutorDTO;
import com.orbitech.npvet.dto.VacinaDTO;
import com.orbitech.npvet.entity.Contato;
import com.orbitech.npvet.entity.Endereco;
import com.orbitech.npvet.entity.Tutor;
import com.orbitech.npvet.entity.Vacina;
import com.orbitech.npvet.repository.TutorRepository;
import com.orbitech.npvet.service.TutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TutorServiceTest {
    @Autowired
    private TutorService service;
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
        when(repository.findAll()).thenReturn(tutorEntityList);
        when(repository.getAllAtivados()).thenReturn(tutorEntityList);
        when(repository.getAllDesativados()).thenReturn(tutorEntityList);
        when(repository.save(Mockito.any(Tutor.class))).thenReturn(tutorEntity);
    }
    @Test
    void tutorDtoToTamanhoEntityTest(){
        Tutor tutor = service.toTutor(tutorDTO);
        assertThat(tutor).usingRecursiveComparison().isEqualTo(tutorEntity);
    }

    @Test
    void tutorToTamanhoDTOTest(){
        TutorDTO tutor = service.toTutorDTO(tutorEntity);
        assertThat(tutor).usingRecursiveComparison().isEqualTo(tutorDTO);
    }
    @Test
    void tutorFindByIdTest(){
        TutorDTO retornoService = service.getById(1L);
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tutorDTO);
    }
    @Test
    void tutorGetAllTest(){
        List<TutorDTO> retornoService = service.getAll();
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tutorEntityList);
    }
    @Test
    void tutorGetAllAtivadosTest(){
        List<TutorDTO> retornoService = service.getAllAtivados();
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tutorEntityList);
    }
    @Test
    void tutorGetAllDesativadosTest(){
        List<TutorDTO> retornoService = service.getAllAtivados();
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tutorEntityList);
    }
    @Test
    void tutorGetAllByNomeTest(){
        List<TutorDTO> retornoService = service.getAllByNome("Nome");
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tutorEntityList);
    }

    @Test
    void tutorCadastrarTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            service.create(tutorDTO);
        });
    }
    @Test
    void tutorEditarTest(){
        TutorDTO retornoService = service.update(1L, tutorDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(tutorDTO);
    }

    @Test
    void tutorDeletarTest() {
        service.delete(1L);
        verify(repository,times(1)).findById(1L);
    }
}
