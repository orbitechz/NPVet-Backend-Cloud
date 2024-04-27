package com.orbitech.npvet.service;

import com.orbitech.npvet.dto.ConsultaDTO;

import com.orbitech.npvet.entity.Consulta;
import com.orbitech.npvet.entity.Status;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.ConsultaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.orbitech.npvet.service.AnamneseService.NOT_FOUND_MESSAGE;

@Service
@Slf4j
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public ConsultaDTO toConsultaDTO(Consulta consultaEntidade) {
        ConsultaDTO consultaDTO = mapper.map(consultaEntidade, ConsultaDTO.class);

        // Map the Anamnese attribute separately if it is not null
//        Anamnese anamnese = consultaEntidade.getAnamnese();
//        if (anamnese != null) {
//            AnamneseDTO anamneseDTO = mapper.map(anamnese, AnamneseDTO.class);
//            consultaDTO.setAnamnese(anamneseDTO);
//        }

        return consultaDTO;
    }



    public Consulta toConsultaEntidade(ConsultaDTO consultaDTO){
        Consulta consulta = mapper.map(consultaDTO, Consulta.class);
//
//        AnamneseDTO anamnese = consultaDTO.getAnamnese();
//        if (anamnese != null) {
//            Anamnese anamneseDTO = mapper.map(anamnese, Anamnese.class);
//            consulta.setAnamnese(anamneseDTO);
//        }
        return consulta;
    }

    public ConsultaDTO getById(Long id) {
        Consulta consulta = repository.findById(id).orElse(null);
        Assert.notNull(consulta, String.format(NOT_FOUND_MESSAGE, id));
        return toConsultaDTO(consulta);
    }


    public List<ConsultaDTO> getAll(){
        return repository.findAll().stream().map(this::toConsultaDTO).toList();
    }

    public ConsultaDTO create(ConsultaDTO consultaDTO, Usuario usuario) {
        Consulta consultaSaved = repository.save(toConsultaEntidade(consultaDTO));
        log.info("CONSULTA: " + consultaSaved.getId() + " DO TUTOR: "+ consultaSaved.getTutor() + " COM ANIMAL: " + consultaSaved.getAnimal().getNome() + " CRIADA POR: " + usuario.getNome() + " ID: " + usuario.getId());
        return toConsultaDTO(consultaSaved);
    }

    public ConsultaDTO update(long id, ConsultaDTO consultaDTO, Usuario usuario) {
        Assert.notNull(repository.findById(id).orElse(null),String.format("ID [%s] não localizado,",id));
        Consulta consultaSaved = repository.save(toConsultaEntidade(consultaDTO));
        log.info("CONSULTA: " + consultaSaved.getId() + " DO TUTOR: "+ consultaSaved.getTutor() + " COM ANIMAL: " + consultaSaved.getAnimal().getNome() + " EDITADA POR: " + usuario.getNome() + " ID: " + usuario.getId());
        return toConsultaDTO(consultaSaved);
    }

    public void delete(long id, Usuario usuario) {
        Consulta consultaDeleted = repository.findById(id).orElse(null);
        Assert.notNull(consultaDeleted ,String.format("ID [%s] não localizado,",id));
        repository.deleteById(id);
        log.info("CONSULTA: " + consultaDeleted.getId() + " DO TUTOR: "+ consultaDeleted.getTutor() + " COM ANIMAL: " + consultaDeleted.getAnimal().getNome() + " DELETADA POR: " + usuario.getNome() + " ID: " + usuario.getId());
    }
    public List<ConsultaDTO>getAnimalByName(String nome){
        List<ConsultaDTO> retorno = repository.findConsultaByAnimalName(nome)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhum animal com o nome: {%s}",nome.toUpperCase()));
        return retorno;
    }

    public List<ConsultaDTO>getAnimalById(Long id){
        List<ConsultaDTO>retorno = repository.findConsultaByAnimalId(id)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhum animal com o Id: {%s}",id));
        return retorno;
    }

    public List<ConsultaDTO>getVeterinarioByName(String nome){
        List<ConsultaDTO>retorno = repository.findConsultaByVeterinarioNome(nome)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhum veterinário com o nome: {%s}",nome.toUpperCase()));
        return retorno;
    }
    public List<ConsultaDTO>getVeterinarioById(Long id){
        List<ConsultaDTO>retorno = repository.findConsultaByVeterinarioId(id)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhum veterinário com o nome: {%s}", id));
        return retorno;
    }

    public List<ConsultaDTO>getAnamneseId(Long id){
        List<ConsultaDTO>retorno = repository.findConsultaByAnamneseId(id)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhuma anamnese com o Id: {%s}",id));
        return retorno;
    }

    public List<ConsultaDTO>getConsultasEmAndamento(){
        List<ConsultaDTO>retorno = repository.findConsultasByStatus(Status.EM_ANDAMENTO)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Não encontramos nenhuma consulta em andamento.");
        return retorno;
    }

    public List<ConsultaDTO>getConsultasConcluida(){
        List<ConsultaDTO>retorno = repository.findConsultasByStatus(Status.CONCLUIDA)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Nenhuma consulta foi concluída ainda.");
        return retorno;
    }

    public List<ConsultaDTO>getConsultasCancelada(){
        List<ConsultaDTO>retorno = repository.findConsultasByStatus(Status.CANCELADA)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Não encontramos nenhuma consulta cancelada!");
        return retorno;
    }

    public List<ConsultaDTO>getConsultaAgendada(){
        List<ConsultaDTO>retorno = repository.findConsultasByStatus(Status.AGENDADA)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(), "Não encontramos nenhuma consulta agendada!");
        return retorno;
    }


    public List<ConsultaDTO> getFilteredConsultas(LocalDateTime startDate,
                                                  LocalDateTime endDate,
                                                  Long animalId,
                                                  Status status
                                                  ) {
        return repository.findFilteredConsultas(startDate,
                endDate,
                animalId,
                status).stream().map(this::toConsultaDTO).toList();
    }



}