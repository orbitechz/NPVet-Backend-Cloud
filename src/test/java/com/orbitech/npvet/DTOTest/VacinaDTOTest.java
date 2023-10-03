package com.orbitech.npvet.DTOTest;

import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.VacinaDTO;
import com.orbitech.npvet.entity.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VacinaDTOTest {
    private VacinaDTO vacinaDTO = new VacinaDTO();
    private LocalDate aplicacao = LocalDate.of(2023, Month.SEPTEMBER, 26);

    @BeforeEach
    void setup() {

        vacinaDTO.setId(1L);
        vacinaDTO.setNome("Vacina");
        vacinaDTO.setDescricao("Descricao");
        vacinaDTO.setDataAplicacao(aplicacao);
        vacinaDTO.setDataRetorno(aplicacao);
        vacinaDTO.setAnimal(new AnimalDTO());
    }
    @Test
    void vacinaIdTest(){
        assertEquals(1L, vacinaDTO.getId());
    }
    @Test
    void vacinaNomeTest(){
        assertEquals("Vacina", vacinaDTO.getNome());
    }
    @Test
    void vacinaDescricaoTest(){
        assertEquals("Descricao", vacinaDTO.getDescricao());
    }
    @Test
    void vacinaAplicacaoTest(){
        assertEquals(aplicacao, vacinaDTO.getDataAplicacao());
    }
    @Test
    void vacinaRetornoTest(){
        assertEquals(aplicacao, vacinaDTO.getDataRetorno());
    }
    @Test
    void vacinaAnimalTest(){
        assertThat(vacinaDTO.getAnimal()).usingRecursiveComparison().isEqualTo(new Animal());
    }
    @Test
    void vacinaAllArgsConstructorTest(){
        VacinaDTO vacinaAllArgs = new VacinaDTO("Vacina", aplicacao, aplicacao, "Descricao", new AnimalDTO());
        assertThat(vacinaAllArgs).usingRecursiveComparison().ignoringFields("id").isEqualTo(vacinaDTO);
    }
}