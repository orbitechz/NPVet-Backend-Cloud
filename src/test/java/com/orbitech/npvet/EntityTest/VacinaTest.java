package com.orbitech.npvet.EntityTest;

import com.orbitech.npvet.entity.Animal;
import com.orbitech.npvet.entity.Vacina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VacinaTest {
    private Vacina vacina = new Vacina();
    private LocalDate aplicacao = LocalDate.of(2023, Month.SEPTEMBER, 26);
    @BeforeEach
    void setup() {

        vacina.setId(1L);
        vacina.setNome("Vacina");
        vacina.setDescricao("Descricao");
        vacina.setDataAplicacao(aplicacao);
        vacina.setDataRetorno(aplicacao);
        vacina.setAnimal(new Animal());
    }
    @Test
    void vacinaIdTest(){
        assertEquals(1L, vacina.getId());
    }
    @Test
    void vacinaNomeTest(){
        assertEquals("Vacina", vacina.getNome());
    }
    @Test
    void vacinaDescricaoTest(){
        assertEquals("Descricao", vacina.getDescricao());
    }
    @Test
    void vacinaAplicacaoTest(){
        assertEquals(aplicacao, vacina.getDataAplicacao());
    }
    @Test
    void vacinaRetornoTest(){
        assertEquals(aplicacao, vacina.getDataRetorno());
    }
    @Test
    void vacinaAnimalTest(){
        assertThat(vacina.getAnimal()).usingRecursiveComparison().isEqualTo(new Animal());
    }
    @Test
    void vacinaAllArgsConstructorTest(){
        Vacina vacinaAllArgs = new Vacina("Vacina", aplicacao, aplicacao, "Descricao", new Animal());
        assertThat(vacinaAllArgs).usingRecursiveComparison().ignoringFields("id").isEqualTo(vacina);
    }
}