package com.orbitech.npvet.ServiceTest;

import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.DTO.VacinaDTO;
import com.orbitech.npvet.Entity.Animal;
import com.orbitech.npvet.Entity.Vacina;
import com.orbitech.npvet.Repository.AnimalRepository;
import com.orbitech.npvet.Repository.VacinaRepository;
import com.orbitech.npvet.Service.VacinaService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VacinaServiceTest {
    @Autowired
    private VacinaService service;
    @MockBean
    private VacinaRepository repository;
    @MockBean
    private AnimalRepository animalRepository;

    Vacina vacinaEntity = new Vacina();
    VacinaDTO vacinaDTO = new VacinaDTO();
    Animal animalEntity = new Animal();
    AnimalDTO animalDTO = new AnimalDTO();
    List<Vacina> vacinaEntityList = new ArrayList<>();
    List<VacinaDTO> vacinaDTOList = new ArrayList<>();

    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);

        animalEntity.setId(1L);
        animalDTO.setId(1L);

        vacinaDTO.setId(1L);
        vacinaDTO.setNome("Vacina");
        vacinaDTO.setDescricao("Descricao");
        vacinaDTO.setAnimal(animalDTO);
        vacinaDTOList.add(vacinaDTO);

        vacinaEntity.setId(1L);
        vacinaEntity.setNome("Vacina");
        vacinaEntity.setDescricao("Descricao");
        vacinaEntity.setAnimal(animalEntity);
        vacinaEntityList.add(vacinaEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(vacinaEntity));
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animalEntity));
        when(repository.findAllByAnimalId(Mockito.any(Long.class))).thenReturn(vacinaEntityList);
        when(repository.findAllByNomeLike(Mockito.any(String.class))).thenReturn(vacinaEntityList);
        when(repository.save(Mockito.any(Vacina.class))).thenReturn(vacinaEntity);
    }

    @Test
    void vacinaDtoToTamanhoEntityTest(){
        Vacina tamanho = service.toVacina(vacinaDTO);
        assertThat(tamanho).usingRecursiveComparison().isEqualTo(vacinaEntity);
    }

    @Test
    void vacinaToTamanhoDTOTest(){
        VacinaDTO tamanho = service.toVacinaDTO(vacinaEntity);
        assertThat(tamanho).usingRecursiveComparison().isEqualTo(vacinaDTO);
    }
    @Test
    void vacinaFindByIdTest(){
        VacinaDTO retornoService = service.getById(1L);
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(vacinaDTO);
    }
    @Test
    void vacinaGetAllTest(){
        List<VacinaDTO> retornoService = service.getAllByNome("Vacina");
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(vacinaDTOList);
    }

    @Test
    void vacinaGetAllByNomeTest(){
        List<VacinaDTO> retornoService = service.getByAnimal(1L);
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(vacinaDTOList);
    }

    @Test
    void vacinaCadastrarTest(){
        vacinaDTO.setId(null);
        VacinaDTO retornoService = service.create(vacinaDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().ignoringFields("id").isEqualTo(vacinaDTO);
    }
    @Test
    void vacinaEditarTest(){
        VacinaDTO retornoService = service.update(1L, vacinaDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(vacinaDTO);
    }

    @Test
    void vacinaDeletarTest() {
        service.delete(1L);
        Mockito.verify(repository, times(1)).deleteById(1L);
    }

}
