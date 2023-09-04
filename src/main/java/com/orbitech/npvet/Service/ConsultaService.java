package com.orbitech.npvet.Service;
import com.orbitech.npvet.DTO.ConsultaDTO;
import com.orbitech.npvet.Entity.Consulta;
import com.orbitech.npvet.Repository.ConsultaRepository;
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
}
