package com.orbitech.npvet.ServiceTest;

import com.orbitech.npvet.dto.*;
import com.orbitech.npvet.entity.*;
import com.orbitech.npvet.repository.AnamneseHistoricoRepository;
import com.orbitech.npvet.repository.AnamnesePerguntaRepository;
import com.orbitech.npvet.repository.AnamneseRepository;
import com.orbitech.npvet.service.AnamneseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class AnamneseServiceTest {

    @InjectMocks
    private AnamneseService anamneseService;

    @Mock
    private AnamneseRepository anamneseRepository;

    @Mock
    private AnamneseHistoricoRepository anamneseHistoricoRepository;

    @Mock
    private AnamnesePerguntaRepository anamnesePerguntaRepository;

    @Mock
    private ModelMapper modelMapper;
    AnimalDTO animalDTO = new AnimalDTO();
    Animal animal = new Animal();
    TutorDTO tutorDTO = new TutorDTO();
    Tutor tutor = new Tutor();
    UsuarioDTO veterinarioDTO = new UsuarioDTO();
    Usuario veterinario = new Usuario();
    AnamneseHistoricoDTO anamneseHistoricoDTO = new AnamneseHistoricoDTO();
    AnamneseHistorico anamneseHistorico = new AnamneseHistorico();
    AnamneseDTO anamneseDTO = new AnamneseDTO();
    Anamnese anamnese = new Anamnese();
    AnamnesePergunta anamnesePergunta = new AnamnesePergunta();
    AnamnesePerguntaDTO anamnesePerguntaDTO = new AnamnesePerguntaDTO();
    List<AnamneseDTO> anamneseDTOList = new ArrayList<>();
    List<Anamnese> anamneseList = new ArrayList<>();
    List<AnamneseHistoricoDTO> anamneseHistoricoDTOs = new ArrayList<>();
    List<AnamneseHistorico> anamneseHistoricos = new ArrayList<>();

    @BeforeEach
    void setUp(){

        // Animal DTO e Entity
        animalDTO.setId(1L);
        animalDTO.setNome("Buddy");

        animal.setId(1L);
        animal.setNome("Buddy");

        // Tutor DTO e Entity
        tutorDTO.setId(1L);
        tutorDTO.setCpf("123");

        tutor.setId(1L);
        tutor.setCpf("123");

        // Veterinario DTO e Entity
        veterinarioDTO.setId(1L);
        veterinarioDTO.setNome("Dr. Smith");

        veterinario.setId(1L);
        veterinario.setNome("Dr. Smith");

        // AnamneseHistorico DTO e Entity
        anamneseHistoricoDTO.setId(1L);
        anamneseHistoricoDTO.setAnamnese(anamneseDTO);
        anamneseHistoricoDTO.setProgressoMedico("Medical Progress Sample One.");

        anamneseHistorico.setId(1L);
        anamneseHistorico.setAnamnese(anamnese);
        anamneseHistorico.setProgressoMedico("Medical Progress Sample One.");

        // Setting AnamneseHistorico Lists
        anamneseHistoricoDTOs.add(anamneseHistoricoDTO);
        anamneseHistoricos.add(anamneseHistorico);

        // Creating the anamneses
        anamneseDTO.setId(1L);
        anamneseDTO.setAnimalDTO(animalDTO);
        anamneseDTO.setTutorDTO(tutorDTO);
        anamneseDTO.setVeterinarioDTO(veterinarioDTO);
        anamneseDTO.setHistoricoProgressoMedico(anamneseHistoricoDTOs);

        anamnese.setId(1L);
        anamnese.setAnimal(animal);
        anamnese.setTutor(tutor);
        anamnese.setVeterinario(veterinario);
        anamnese.setHistoricoProgressoMedico(anamneseHistoricos);

        // Setting Values of Anamnese List
        anamneseDTOList.add(anamneseDTO);
        anamneseList.add(anamnese);

        when(anamneseRepository.findById(1L)).thenReturn(Optional.of(anamnese));
        when(anamneseRepository.findAll()).thenReturn(anamneseList);
        when(anamneseRepository.findByTutorCpf("123")).thenReturn(anamneseList);
        when(anamneseRepository.findByTutorCpfAndAnimal("123","Buddy")).thenReturn(anamneseList);
        when(anamneseRepository.save(Mockito.any(Anamnese.class))).thenReturn(anamnese);
        when(anamneseHistoricoRepository.existsByProgressoMedico(anyString())).thenReturn(false);
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

    @Test
    void testCreateAnamneseWithExistingHistorico() {
        when(anamneseHistoricoRepository.existsByProgressoMedico(anyString())).thenReturn(true);
        AnamneseDTO result = anamneseService.create(anamneseDTO);
        verify(anamneseHistoricoRepository, never()).save(any(AnamneseHistorico.class));
        verify(anamneseRepository, times(1)).save(any(Anamnese.class));
        assertNotNull(result);
    }

    @Test
    void testCreateAnamneseWithNonExistingHistorico() {
        AnamneseDTO result = anamneseService.create(anamneseDTO);
        verify(anamneseHistoricoRepository, times(1)).save(any(AnamneseHistorico.class));
        verify(anamneseRepository, times(1)).save(any(Anamnese.class));
        assertNotNull(result);
    }

    @Test
    void testCreateWithNonEmptyHistoricoProgressoMedico() {
        anamnese.setHistoricoProgressoMedico(anamneseHistoricos);

        when(anamneseHistoricoRepository.existsByProgressoMedico(anyString())).thenReturn(false);

        AnamneseDTO result = anamneseService.create(anamneseDTO);
        verify(anamneseHistoricoRepository, times(1)).save(any(AnamneseHistorico.class));
        assertNotNull(result);
    }

    @Test
    void testUpdateAnamnese() {
        AnamneseDTO updatedAnamneseDTO = anamneseService.update(1L, anamneseDTO);

        assertNotNull(updatedAnamneseDTO);
        assertEquals(anamneseDTO, updatedAnamneseDTO);
        verify(anamneseRepository, times(1)).save(any(Anamnese.class));
        verify(anamneseHistoricoRepository, times(1)).save(any(AnamneseHistorico.class));
    }

    @Test
    void testUpdateAnamneseWithMismatchedId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            anamneseService.update(1L + 1, anamneseDTO);
        });
        assertEquals("O ID = 2 solicitado não foi encontrado no banco de dados.", exception.getMessage());
    }

    @Test
    void testUpdateAnamneseWithEmptyHistoricalProgress() {

        anamneseDTO.setHistoricoProgressoMedico(new ArrayList<>());
        AnamneseDTO updatedAnamneseDTO = anamneseService.update(1L, anamneseDTO);

        assertNotNull(updatedAnamneseDTO);
        assertEquals(anamneseDTO, updatedAnamneseDTO);
        verify(anamneseRepository, times(1)).save(any(Anamnese.class));
        verify(anamneseHistoricoRepository, never()).save(any(AnamneseHistorico.class));
    }

    @Test
    void testUpdateProgressoMedico() {

        when(anamneseHistoricoRepository.save(any(AnamneseHistorico.class))).thenAnswer(invocation -> {
            AnamneseHistorico savedHistorico = invocation.getArgument(0);
            savedHistorico.setId(1L);
            return savedHistorico;
        });

        AnamneseHistoricoDTO result = anamneseService.updateProgressoMedico(1L, anamneseHistoricoDTO);
        verify(anamneseRepository, times(1)).save(anamnese);
        verify(anamneseHistoricoRepository, times(1)).save(any(AnamneseHistorico.class));

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(anamneseHistoricoDTO.getProgressoMedico(), result.getProgressoMedico());
    }
}
