package com.orbitech.npvet.Service;
import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Entity.Anamnese;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Repository.AnamneseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnamneseService {

    private AnamneseRepository anamneseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AnamneseService(AnamneseRepository anamneseRepository, ModelMapper modelMapper) {
        this.anamneseRepository = anamneseRepository;
        this.modelMapper = modelMapper;
    }

    public AnamneseDTO getById(Long id) {return null;}

    public List<AnamneseDTO> getAll() {
        return null;
    }

    public List<AnamneseDTO> getByTutorCpf(String cpf) {
        return null;
    }

    public List<AnamneseDTO> getByTutorCpfAndAnimal(String cpf) {
        return null;
    }

    public Anamnese create(AnamneseDTO anamneseDTO) {
        return null;
    }

    public Anamnese update(Long id, AnamneseDTO anamneseDTO) {
        return null;
    }

    public boolean delete(Long id) {
        return false;
    }

    public AnamneseDTO toAnamneseDTO(Anamnese anamnese){
        return modelMapper.map(anamnese, AnamneseDTO.class);
    }

    public Anamnese toAnamnese(AnamneseDTO anamneseDTO){
        return modelMapper.map(anamneseDTO, Anamnese.class);
    }
}
