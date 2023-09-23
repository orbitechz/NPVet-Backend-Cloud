package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.Controller.AnamneseController;
import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.DTO.UsuarioDTO;
import com.orbitech.npvet.Entity.Anamnese;
import com.orbitech.npvet.Entity.Animal;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Entity.Usuario;
import com.orbitech.npvet.Repository.AnamneseRepository;
import com.orbitech.npvet.Service.AnamneseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AnamneseControllerTest {

    @InjectMocks
    private AnamneseController anamneseController;

    @MockBean
    private AnamneseService anamneseService;

    @MockBean
    private AnamneseRepository anamneseRepository;

    AnamneseDTO anamneseDTO = new AnamneseDTO();
    Anamnese anamnese = new Anamnese();
    List<AnamneseDTO> anamneseDTOList = new ArrayList<>();
    List<Anamnese> anamneseList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
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
        when(anamneseService.getByTutorCpfAndAnimal("123", "Buddy")).thenReturn(anamneseDTOList);
        when(anamneseRepository.save(Mockito.any(Anamnese.class))).thenReturn(anamnese);
    }

    @Test
    void getByIdTest(){
        ResponseEntity<AnamneseDTO> response = anamneseController.getById(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllTest(){
        ResponseEntity<List<AnamneseDTO>> response = anamneseController.getAll();
        assertNotNull(response);
        assertEquals(2,response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getByTutorCpfTest() {

        System.out.println("DEBUG: Before method call, anamneseDTOList: " + anamneseDTOList);

        ResponseEntity<List<AnamneseDTO>> response = anamneseController.getByTutorCpf("123");

        System.out.println("DEBUG: After method call, response: " + response.getBody());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<AnamneseDTO> responseBody = response.getBody();
        assertEquals(anamneseDTOList, responseBody);

        verify(anamneseService).getByTutorCpf("123");
    }


    @Test
    void getByTutorCpfAndAnimal(){

        ResponseEntity<List<AnamneseDTO>> response =
                anamneseController.getByTutorCpfAndAnimal("cpf_do_tutor", "Buddy");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<AnamneseDTO> responseBody = response.getBody();
        assertEquals(anamneseDTOList, responseBody);

        verify(anamneseService).getByTutorCpfAndAnimal("cpf_do_tutor", "Buddy");
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
