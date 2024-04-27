package com.orbitech.npvet.service;

import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.ExameFisicoDTO;
import com.orbitech.npvet.entity.ExameFisico;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.ExameFisicoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
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

    public List<ExameFisicoDTO> getByNomeAnimal(String nome){
        return repository.findAllByNomeLike(nome).stream().map(this::toExameDTO).toList();
    }

    public List<ExameFisicoDTO> getByIdAnimal(Long id){
        return repository.findAllById(id).stream().map(this::toExameDTO).toList();
    }


    @Transactional
    public ExameFisicoDTO create(ExameFisicoDTO exameFisicoDTO, Usuario usuario){

        AnimalDTO animalBanco =  animalService.getById(exameFisicoDTO.getAnimal().getId());
        Assert.notNull(animalBanco, "Animal inexistente");
        ExameFisico exameFisico = repository.save(toExame(exameFisicoDTO));
        log.info("EXAME FISICO: " + exameFisico.getId() + " DA CONSULTA: " + exameFisico.getConsulta().getId() +  " TUTOR: "+ exameFisico.getAnimal().getTutorId().getNome() + " COM ANIMAL: " + exameFisico.getAnimal().getNome()
                + " CRIADA POR: " + usuario.getId() + " NOME: " + usuario.getNome()
        );
        return toExameDTO(exameFisico);
    }

    @Transactional
    public ExameFisicoDTO update(Long id, ExameFisicoDTO exameFisicoDTO, Usuario usuario){
        ExameFisicoDTO exameFisicoBanco = getById(id);
        Assert.notNull(exameFisicoBanco, String.format("Exame com ID %s não existe!", id));
        Assert.isTrue(id.equals(exameFisicoDTO.getId()), "O Exame informado não é o mesmo a ser atualizado!");
        ExameFisico exameFisico = repository.save(toExame(exameFisicoDTO));
        log.info("EXAME FISICO: " + exameFisico.getId() + " DA CONSULTA: " + exameFisico.getConsulta().getId() +  " TUTOR: "+ exameFisico.getAnimal().getTutorId().getNome() + " COM ANIMAL: " + exameFisico.getAnimal().getNome()
                + " EDITADA POR: " + usuario.getId() + " NOME: " + usuario.getNome()
        );

        return toExameDTO(exameFisico);
    }

    @Transactional
    public void delete(Long id, Usuario usuario){
        ExameFisico exameFisico = repository.findById(id).orElse(null);
        Assert.notNull(exameFisico,String.format("ID [%s] não localizado.",id));
        exameFisico.setDeletedAt(LocalDateTime.now());
        repository.save(exameFisico);
        log.info("EXAME FISICO: " + exameFisico.getId() + " DA CONSULTA: " + exameFisico.getConsulta().getId() +  " TUTOR: "+ exameFisico.getAnimal().getTutorId().getNome() + " COM ANIMAL: " + exameFisico.getAnimal().getNome()
                + " EDITADA POR: " + usuario.getId() + " NOME: " + usuario.getNome()
        );
    }


}
