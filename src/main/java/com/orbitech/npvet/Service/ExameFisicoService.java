package com.orbitech.npvet.Service;

import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.DTO.ExameFisicoDTO;
import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Entity.Animal;
import com.orbitech.npvet.Entity.ExameFisico;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Repository.ExameFisicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ExameFisicoService {

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private ExameFisicoRepository repository;

    @Autowired
    private AnimalService animalService;

    public ExameFisicoDTO toExameDTO(ExameFisico exameFisico){return mapper.map(exameFisico, ExameFisicoDTO.class);}

    public ExameFisico toExame(ExameFisicoDTO exameFisicoDTO){return mapper.map(exameFisicoDTO, ExameFisico.class);}

    public ExameFisicoDTO getById(Long id){
        ExameFisico exameFisico = repository.findById(id).orElse(null);
        Assert.notNull(exameFisico, String.format("Exame Fisico com ID %s não existe!", id));
        return toExameDTO(exameFisico);
    }

    public List<ExameFisicoDTO> getAll(){
        return repository.findAll().stream().map(this::toExameDTO).toList();
    }

    @Transactional
    public ExameFisicoDTO create(ExameFisicoDTO exameFisicoDTO){

        AnimalDTO animalBanco =  animalService.getById(exameFisicoDTO.getAnimal().getId());
        Assert.notNull(animalBanco, String.format("Animal inexistente"));

        return toExameDTO(repository.save(toExame(exameFisicoDTO)));
    }

    @Transactional
    public ExameFisicoDTO update(Long id, ExameFisicoDTO exameFisicoDTO){
        ExameFisicoDTO exameFisicoBanco = getById(id);
        Assert.notNull(exameFisicoBanco, String.format("Exame com ID %s não existe!", id));
        Assert.isTrue(id.equals(exameFisicoDTO.getId()), "O Exame informado não é o mesmo a ser atualizado!");
        return toExameDTO(repository.save(toExame(exameFisicoDTO)));
    }

    @Transactional
    public void delete(Long id){
        ExameFisicoDTO exameFisicoDTO = getById(id);
        exameFisicoDTO.setDeletedAt(LocalDateTime.now());
        repository.save(toExame(exameFisicoDTO));
    }


}
