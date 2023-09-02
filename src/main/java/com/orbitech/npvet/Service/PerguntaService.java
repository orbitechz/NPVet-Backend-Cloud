package com.orbitech.npvet.Service;

import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.DTO.PerguntaDTO;
import com.orbitech.npvet.Entity.Anamnese;
import com.orbitech.npvet.Entity.Pergunta;
import com.orbitech.npvet.Repository.AnamneseRepository;
import com.orbitech.npvet.Repository.PerguntaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PerguntaService(PerguntaRepository perguntaRepository, ModelMapper modelMapper) {
        this.perguntaRepository = perguntaRepository;
        this.modelMapper = modelMapper;
    }

    public PerguntaDTO getById(Long id) {
        return null;
    }

    public List<PerguntaDTO> getAll() {
        return null;
    }

    public Pergunta create(PerguntaDTO perguntaDTO) {
        return null;
    }

    public Pergunta update(Long id, PerguntaDTO perguntaDTO) {
        return null;
    }

    public void delete(Long id) {
    }

    public PerguntaDTO toPerguntaDTO(Pergunta pergunta){
        return modelMapper.map(pergunta, PerguntaDTO.class);
    }

    public Pergunta toPergunta(PerguntaDTO perguntaDTO){
        return modelMapper.map(perguntaDTO, Pergunta.class);
    }
}
