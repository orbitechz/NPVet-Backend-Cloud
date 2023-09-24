package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.Controller.ConsultaController;
import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.DTO.ConsultaDTO;
import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Entity.Consulta;
import com.orbitech.npvet.Repository.ConsultaRepository;
import com.orbitech.npvet.Service.ConsultaService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ConsultaTest {

    @MockBean
    private ConsultaRepository repository;
    @Autowired
    private ConsultaController controller;
    @Autowired
    private ConsultaService service;

    private Consulta consultaEntidade = new Consulta();
    private ConsultaDTO consultaDTO = new ConsultaDTO();
    private List<Consulta> consultaList = new ArrayList<>();
    private List<ConsultaDTO>consultaDTOList = new ArrayList<>();
    private List<AnimalDTO>animalDTOList = new ArrayList<>();
    private List<TutorDTO>tutorDTOList = new ArrayList<>();
    private List<AnamneseDTO>anamneseDTOList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        animalDTOList.add(new AnimalDTO());
        tutorDTOList.add(new TutorDTO());
        anamneseDTOList.add(new AnamneseDTO());

        consultaDTO.setAnimal((AnimalDTO) animalDTOList);
        consultaDTO.setTutor((TutorDTO) tutorDTOList);
        consultaDTO.setAnamnese(anamneseDTOList);
        consultaDTO.setData(LocalDateTime.now());
        consultaDTO.set
    }



}
