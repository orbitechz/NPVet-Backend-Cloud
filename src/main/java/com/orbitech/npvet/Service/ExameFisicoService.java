package com.orbitech.npvet.Service;

import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.DTO.ExameFisicoDTO;
import com.orbitech.npvet.Entity.Animal;
import com.orbitech.npvet.Entity.ExameFisico;
import com.orbitech.npvet.Repository.ExameFisicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ExameFisicoService {

    private final ModelMapper mapper = new ModelMapper();

    private ExameFisicoRepository repository;

    public ExameFisicoDTO toExameDTO(ExameFisico exameFisico){return mapper.map(exameFisico, ExameFisicoDTO.class);}

    public ExameFisico toExame(ExameFisicoDTO exameFisicoDTO){return mapper.map(exameFisicoDTO, ExameFisico.class);}

    public ExameFisicoDTO getById(Long id){
        ExameFisico exameFisico = repository.findById(id).orElse(null);
        Assert.notNull(exameFisico, String.format("Exame Fisico com ID %s não existe!", id));
        return toExameDTO(exameFisico);
    }



}
