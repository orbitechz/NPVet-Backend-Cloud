package com.orbitech.npvet.service;
import com.orbitech.npvet.dto.ConsultaDTO;
import com.orbitech.npvet.entity.Consulta;
import com.orbitech.npvet.entity.Status;
import com.orbitech.npvet.repository.ConsultaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public ConsultaDTO toConsultaDTO(Consulta consultaEntidade){
        return mapper.map(consultaEntidade, ConsultaDTO.class);
    }

    public Consulta toConsultaEntidade(ConsultaDTO consultaDTO){
        return mapper.map(consultaDTO, Consulta.class);
    }

    public ConsultaDTO getById (long id) throws Exception{
        return toConsultaDTO(repository.findById(id).orElseThrow(()-> new Exception(String.format("Consulta com o id [%s] não localizado.",id))));
    }

    public List<ConsultaDTO> getAll(){
        return repository.findAll().stream().map(this::toConsultaDTO).toList();
    }

    public ConsultaDTO create(ConsultaDTO consultaDTO) {
        return toConsultaDTO(repository.save(toConsultaEntidade(consultaDTO)));
    }

    public ConsultaDTO update(long id, ConsultaDTO consultaDTO) {
        Assert.notNull(repository.findById(id).orElse(null),String.format("ID [%s] não localizado,",id));
        return toConsultaDTO(repository.save(toConsultaEntidade(consultaDTO)));
    }

    public void delete(long id) {
        Assert.notNull(repository.findById(id).orElse(null),String.format("ID [%s] não localizado,",id));
        repository.deleteById(id);
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

    public List<ConsultaDTO>getConsultasEmAndamento(Status status){
        List<ConsultaDTO>retorno = repository.findConsultasByStatus(status.EM_ANDAMENTO)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhuma consulta em andamento."));
        return retorno;
    }

    public List<ConsultaDTO>getConsultasConcluida(Status status){
        List<ConsultaDTO>retorno = repository.findConsultasByStatus(status.CONCLUIDA)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Nenhuma consulta foi concluída ainda."));
        return retorno;
    }

    public List<ConsultaDTO>getConsultasCancelada(Status status){
        List<ConsultaDTO>retorno = repository.findConsultasByStatus(status.CANCELADA)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhuma consulta cancelada!"));
        return retorno;
    }

    public List<ConsultaDTO>getConsultaAgendada(Status status){
        List<ConsultaDTO>retorno = repository.findConsultasByStatus(status.AGENDADA)
                .stream()
                .map(this::toConsultaDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhuma consulta agendada!"));
        return retorno;
    }

}
