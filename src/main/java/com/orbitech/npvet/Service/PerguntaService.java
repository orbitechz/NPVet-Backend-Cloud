package com.orbitech.npvet.Service;

import com.orbitech.npvet.DTO.PerguntaDTO;
import com.orbitech.npvet.Entity.Pergunta;
import com.orbitech.npvet.Repository.PerguntaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Collections;
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
        Pergunta pergunta = perguntaRepository.findById(id).orElse(null);
        Assert.notNull(pergunta, String.format("O ID = %s solicitado não foi encontrado no banco de dados.", id));
        return toPerguntaDTO(pergunta);
    }

    public List<PerguntaDTO> getAll() {
        List<PerguntaDTO> perguntaDTOS = perguntaRepository.findAll()
                .stream()
                .map(this::toPerguntaDTO)
                .toList();

        if (perguntaDTOS.isEmpty()) {
            return Collections.emptyList();
        }

        return perguntaDTOS;
    }

    public Pergunta create(PerguntaDTO perguntaDTO) {

        if (perguntaRepository.existsByEnunciado(perguntaDTO.getEnunciado())) {
            throw new DataIntegrityViolationException("O enunciado deve ser único.");
        }
        return perguntaRepository.save(toPergunta(perguntaDTO));
    }

    public Pergunta update(Long id, PerguntaDTO perguntaDTO) {

        Pergunta existingPergunta = perguntaRepository.findById(id).orElse(null);
        Assert.notNull(existingPergunta, String.format("O ID = %s solicitado não foi encontrado no banco de dados.", id));

        if (!id.equals(perguntaDTO.getId())) {
            throw new IllegalArgumentException("O ID na URL não corresponde ao ID no corpo da requisição.");
        }

        String enunciadoAtualizado = perguntaDTO.getEnunciado();
        Pergunta existenteComMesmoEnunciado = perguntaRepository.findByEnunciado(enunciadoAtualizado);
        if (existenteComMesmoEnunciado != null && !existenteComMesmoEnunciado.getId().equals(id)) {
            throw new IllegalArgumentException("O enunciado deve ser único, e o enunciado fornecido já existe.");
        }

        return perguntaRepository.save(toPergunta(perguntaDTO));
    }

    public void delete(Long id) {
        PerguntaDTO perguntaDTO = getById(id);
        perguntaDTO.setDeletedAt(LocalDateTime.now());
        perguntaRepository.save(toPergunta(perguntaDTO));
    }

    public PerguntaDTO toPerguntaDTO(Pergunta pergunta) {
        return modelMapper.map(pergunta, PerguntaDTO.class);
    }

    public Pergunta toPergunta(PerguntaDTO perguntaDTO) {
        return modelMapper.map(perguntaDTO, Pergunta.class);
    }
}
