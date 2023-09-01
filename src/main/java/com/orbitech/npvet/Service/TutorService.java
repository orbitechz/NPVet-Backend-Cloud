package com.orbitech.npvet.Service;

import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Repository.TutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;
    private ModelMapper mapper;

    public TutorDTO toTutorDTO(Tutor tutor){
        return mapper.map(tutor, TutorDTO.class);
    }

    public Tutor toTutor(TutorDTO tutorDTO){
        return mapper.map(tutorDTO, Tutor.class);
    }
}
