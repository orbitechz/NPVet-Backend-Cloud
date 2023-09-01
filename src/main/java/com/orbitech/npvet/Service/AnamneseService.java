package com.orbitech.npvet.Service;
import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Entity.Anamnese;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Repository.AnamneseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
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

    public AnamneseDTO getById(Long id) {
        Anamnese anamnese = anamneseRepository.findById(id).orElse(null);
        Assert.notNull(anamnese, String.format("O ID = %s solicitado não foi encontrado no banco de dados.", id));
        return toAnamneseDTO(anamnese);
    }

    public List<AnamneseDTO> getAll() {
        List<AnamneseDTO> anamneseDTOs = anamneseRepository.findAll()
                .stream()
                .map(this::toAnamneseDTO)
                .toList();

        if (anamneseDTOs.isEmpty()) {
            return Collections.emptyList();
        }

        return anamneseDTOs;
    }

    public List<AnamneseDTO> getByTutorCpf(String cpf) {
        List<AnamneseDTO> anamneseDTOs = anamneseRepository.findByTutorCpf()
                .stream()
                .map(this::toAnamneseDTO)
                .toList();

        Assert.isTrue(!anamneseDTOs.isEmpty(),
                String.format("Nenhuma anamnese encontrada para o tutor com CPF: %s", cpf));

        return anamneseDTOs;
    }

    public List<AnamneseDTO> getByTutorCpfAndAnimal(String cpf,String nome) {
        List<AnamneseDTO> anamneseDTOs = anamneseRepository.findByTutorCpfAndAnimal(cpf,nome)
                .stream()
                .map(this::toAnamneseDTO)
                .toList();

        Assert.isTrue(!anamneseDTOs.isEmpty(),
                String.format("Nenhuma anamnese encontrada para o animal do tutor com CPF: %s", cpf));

        return anamneseDTOs;
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
