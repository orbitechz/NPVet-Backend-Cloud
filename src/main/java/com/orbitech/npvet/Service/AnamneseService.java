package com.orbitech.npvet.Service;
import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.DTO.AnamnesePerguntaDTO;
import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Entity.*;
import com.orbitech.npvet.Repository.AnamneseRepository;
import com.orbitech.npvet.Repository.PerguntaRepository;
import com.orbitech.npvet.Repository.TutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AnamneseService {

    private final AnamneseRepository anamneseRepository;
    private final PerguntaRepository perguntaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AnamneseService(AnamneseRepository anamneseRepository,
                           PerguntaRepository perguntaRepository,
                           ModelMapper modelMapper) {
        this.anamneseRepository = anamneseRepository;
        this.perguntaRepository = perguntaRepository;
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
        List<AnamneseDTO> anamneseDTOs = anamneseRepository.findByTutorCpf(cpf)
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
        return anamneseRepository.save(toAnamnese(anamneseDTO));
    }

    public Anamnese update(Long id, AnamneseDTO anamneseDTO) {
        Anamnese existingAnamnese = anamneseRepository.findById(id).orElse(null);
        Assert.notNull(existingAnamnese, String.format("O ID = %s solicitado não foi encontrado no banco de dados.", id));

        if (!id.equals(existingAnamnese.getId())) {
            throw new IllegalArgumentException("O ID na URL não corresponde ao ID no corpo da requisição.");
        }
        return anamneseRepository.save(toAnamnese(anamneseDTO));
    }

    public void delete(Long id) {
        AnamneseDTO anamneseDTO = getById(id);
        anamneseDTO.setDeletedAt(LocalDateTime.now());
        anamneseRepository.save(toAnamnese(anamneseDTO));
    }

    public AnamneseDTO toAnamneseDTO(Anamnese anamnese){
        return modelMapper.map(anamnese, AnamneseDTO.class);
    }

    public Anamnese toAnamnese(AnamneseDTO anamneseDTO){
        return modelMapper.map(anamneseDTO, Anamnese.class);
    }


    public Anamnese addQuestionAnswerToAnamnese(Long anamneseId, AnamnesePergunta request) {

        // Tentar encontrar a Anamnese pelo seu ID
        Anamnese anamnese = anamneseRepository.findById(anamneseId)
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma anamnese encontrada com o ID: " + anamneseId));

        AnamnesePergunta anamnesePergunta = new AnamnesePergunta();
        anamnesePergunta.setAnamnese(anamnese);

        Pergunta pergunta = perguntaRepository.findById(anamneseId)
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma pergunta encontrada com o ID: " +
                        request.getPergunta().getId()));


        anamnesePergunta.setPergunta(request.getPergunta());
        anamnesePergunta.setResposta(request.getResposta());

        // Adicionar a nova AnamnesePergunta à lista da Anamnese
        anamnese.getAnamnesePerguntas().add(anamnesePergunta);

        // Salvar a Anamnese atualizada no repositório
        return anamneseRepository.save(anamnese);
    }
}
