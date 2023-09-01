package com.orbitech.npvet.Service;
import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.Entity.Anamnese;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnamneseService {

    public AnamneseDTO getById(Long id) {
        return null;
    }

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
}
