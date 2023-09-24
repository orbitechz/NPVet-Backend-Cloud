package com.orbitech.npvet.ServiceTest;

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
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class AnamneseServiceTest {

    @InjectMocks
    private AnamneseService anamneseService;

    @Mock
    private AnamneseRepository anamneseRepository;

    @Mock
    private ModelMapper modelMapper;

    AnamneseDTO anamneseDTO = new AnamneseDTO();
    Anamnese anamnese = new Anamnese();
    List<AnamneseDTO> anamneseDTOList = new ArrayList<>();
    List<Anamnese> anamneseList = new ArrayList<>();

    @BeforeEach
    void setUp() {

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
    void testGetById() {
        AnamneseDTO result = anamneseService.getById(1L);
        assertEquals(anamnese.getId(), result.getId());
        Mockito.verify(anamneseRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAll() {
        List<AnamneseDTO> result = anamneseService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllWithoutAnamneses() {
        when(anamneseRepository.findAll()).thenReturn(Collections.emptyList());
        List<AnamneseDTO> result = anamneseService.getAll();
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetByTutorCpfWithAnamneses() {
        List<AnamneseDTO> result = anamneseService.getByTutorCpfAndAnimal("123","Buddy");
        assertEquals(1, result.size());
    }

    @Test
    void testGetByTutorCpfWithoutTutor() {
        when(anamneseRepository.findByTutorCpf("123")).thenReturn(Collections.emptyList());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            anamneseService.getByTutorCpf("123");
        });
        assertEquals("Nenhuma anamnese encontrada para o tutor com CPF: 123", exception.getMessage());
    }

    @Test
    void testGetByTutorCpfAndAnimalWithTutor() {
        List<AnamneseDTO> result = anamneseService.getByTutorCpfAndAnimal("123","Buddy");
        assertEquals(1, result.size());
    }

    @Test
    void testGetByTutorCpfAndAnimalWithoutTutor() {
        when(anamneseRepository.findByTutorCpfAndAnimal("123","Buddy")).thenReturn(Collections.emptyList());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            anamneseService.getByTutorCpfAndAnimal("123","Buddy");
        });
        assertEquals("Nenhuma anamnese encontrada para o animal do tutor com CPF: 123", exception.getMessage());
    }




}
