package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.controller.AnamneseController;
import com.orbitech.npvet.dto.AnamneseDTO;
import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.TutorDTO;
import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.Anamnese;
import com.orbitech.npvet.entity.Animal;
import com.orbitech.npvet.entity.Tutor;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.AnamneseRepository;
import com.orbitech.npvet.service.AnamneseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class AnamneseControllerTest {

    @Autowired
    private AnamneseController anamneseController;

    @Autowired
    private AnamneseService anamneseService;

    @Mock
    private ModelMapper modelMapper;

    @MockBean
    private AnamneseRepository anamneseRepository;

    AnamneseDTO anamneseDTO = new AnamneseDTO();
    Anamnese anamnese = new Anamnese();
    List<AnamneseDTO> anamneseDTOList = new ArrayList<>();
    List<Anamnese> anamneseList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(1L);
        animalDTO.setNome("Buddy");
        anamneseDTO.setAnimalDTO(animalDTO);

        TutorDTO tutorDTO = new TutorDTO();
        tutorDTO.setId(2L);
        tutorDTO.setNome("Alice");
        tutorDTO.setCpf("123");
        anamneseDTO.setTutorDTO(tutorDTO);

        UsuarioDTO veterinarioDTO = new UsuarioDTO();
        veterinarioDTO.setId(3L);
        veterinarioDTO.setNome("Dr. Smith");
        anamneseDTO.setVeterinarioDTO(veterinarioDTO);

        anamneseDTO.setQueixaPrincipal("Sample queixaPrincipal");
        anamneseDTOList.add(anamneseDTO);

        Animal animal = new Animal();
        animal.setId(1L);
        animal.setNome("Buddy");
        anamnese.setAnimal(animal);

        Tutor tutor = new Tutor();
        tutor.setId(2L);
        tutor.setNome("Alice");
        tutor.setCpf("123");
        anamnese.setTutor(tutor);

        Usuario veterinario = new Usuario();
        veterinario.setId(3L);
        veterinario.setNome("Dr. Smith");
        anamnese.setVeterinario(veterinario);

        anamnese.setQueixaPrincipal("Sample queixaPrincipal");
        anamneseList.add(anamnese);

        when(anamneseRepository.findById(1L)).thenReturn(Optional.of(anamnese));
        when(anamneseRepository.findAll()).thenReturn(anamneseList);
        when(anamneseRepository.findByTutorCpf("123")).thenReturn(anamneseList);
        when(anamneseRepository.findByTutorCpfAndAnimal("123","Buddy")).thenReturn(anamneseList);
        when(modelMapper.map(anamnese, AnamneseDTO.class)).thenReturn(anamneseDTO);
        for (Anamnese anamnese : anamneseList) {
            AnamneseDTO anamneseDTO = new AnamneseDTO();
            when(modelMapper.map(anamnese, AnamneseDTO.class)).thenReturn(anamneseDTO);
            anamneseDTOList.add(anamneseDTO);
        }
    }

    @Test
    void getByIdTest(){
        ResponseEntity<AnamneseDTO> response = anamneseController.getById(1L);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllTest(){
        ResponseEntity<List<AnamneseDTO>> response = anamneseController.getAll();
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getByTutorCpfTest() {
        ResponseEntity<List<AnamneseDTO>> response = anamneseController.getByTutorCpf("123");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnamneseDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
    }


    @Test
    void getByTutorCpfAndAnimal(){
        ResponseEntity<List<AnamneseDTO>> response =
                anamneseController.getByTutorCpfAndAnimal("123", "Buddy");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<AnamneseDTO> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
    }

    @Test
    void createTest(){

    }

    @Test
    @DisplayName("Teste de adição de Perguntas e Respostas à Anamnese")
    void addQuestionAnswerToAnamneseTest(){

    }

    @Test
    @DisplayName("Teste de adição de Progresso Médico")
    void addProgressoMedicoTest(){

    }

    @Test
    void updateTest(){

    }

    @Test
    void deleteTest(){

    }

}
