package com.orbitech.npvet.service;
import com.orbitech.npvet.dto.*;
import com.orbitech.npvet.entity.*;
import com.orbitech.npvet.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class AnamneseService {

    private final AnamneseRepository anamneseRepository;
    private final AnamnesePerguntaRepository anamnesePerguntaRepository ;
    private final AnamneseHistoricoRepository anamneseHistoricoRepository;
    private final PerguntaRepository perguntaRepository;
    private final AnimalRepository animalRepository;
    private final TutorRepository tutorRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public AnamneseService(AnamneseRepository anamneseRepository,
                           AnamnesePerguntaRepository anamnesePerguntaRepository,
                           AnamneseHistoricoRepository anamneseHistoricoRepository,
                           PerguntaRepository perguntaRepository,
                           AnimalRepository animalRepository,
                           TutorRepository tutorRepository) {
        this.anamneseRepository = anamneseRepository;
        this.anamnesePerguntaRepository = anamnesePerguntaRepository;
        this.anamneseHistoricoRepository = anamneseHistoricoRepository;
        this.perguntaRepository = perguntaRepository;
        this.animalRepository = animalRepository;
        this.tutorRepository = tutorRepository;
    }

    private AnamneseHistorico toAnamneseHistorico(AnamneseHistoricoDTO progressoMedico) {
        return modelMapper.map(progressoMedico, AnamneseHistorico.class);
    }

    private AnamnesePergunta toAnamnesePergunta(AnamnesePerguntaDTO request) {
        return modelMapper.map(request, AnamnesePergunta.class);
    }


    public AnamneseDTO toAnamneseDTO(Anamnese anamnese){
        return modelMapper.map(anamnese, AnamneseDTO.class);
    }

    public Anamnese toAnamnese(AnamneseDTO anamneseDTO) {
        return modelMapper.map(anamneseDTO, Anamnese.class);
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

    public AnamneseDTO create(AnamneseDTO anamneseDTO) {
        Anamnese anamnese = toAnamnese(anamneseDTO);
        anamneseRepository.save(anamnese);
        if (!anamnese.getHistoricoProgressoMedico().isEmpty()) {
            for (AnamneseHistorico historico : anamnese.getHistoricoProgressoMedico()) {
                boolean historicoExiste =
                        anamneseHistoricoRepository.existsByProgressoMedico(historico.getProgressoMedico());
                if (!historicoExiste) {
                    historico.setAnamnese(anamnese);
                    historico.setDataAtualizacao(LocalDate.now());
                    anamneseHistoricoRepository.save(historico);
                }
            }
        }
        return anamneseDTO;
    }


    @Transactional
    public AnamneseDTO update(Long id, AnamneseDTO anamneseDTO) {

        Anamnese anamnese = toAnamnese(anamneseDTO);

        Anamnese existingAnamnese = anamneseRepository.findById(id).orElse(null);
        Assert.notNull(existingAnamnese, String.format("O ID = %s solicitado não foi encontrado no banco de dados.", id));

        if (!id.equals(anamnese.getId())) {
            throw new IllegalArgumentException("O ID na URL não corresponde ao ID no corpo da requisição.");
        }

        if (!anamnese.getHistoricoProgressoMedico().isEmpty()) {
            for (AnamneseHistorico historico : anamnese.getHistoricoProgressoMedico()) {
                boolean historicoExiste =
                        anamneseHistoricoRepository.existsByProgressoMedico(historico.getProgressoMedico());
                if (!historicoExiste) {
                    historico.setAnamnese(anamnese);
                    historico.setDataAtualizacao(LocalDate.now());
                    anamneseHistoricoRepository.save(historico);
                }
            }
        }

        anamneseRepository.save(anamnese);
        return anamneseDTO;
    }


    public AnamneseHistoricoDTO updateProgressoMedico(Long id, AnamneseHistoricoDTO progressoMedico) {
        Anamnese existingAnamnese = anamneseRepository.findById(id).orElse(null);
        Assert.notNull(existingAnamnese,
                String.format("O ID = %s solicitado não foi encontrado no banco de dados.", id));

        AnamneseHistorico anamneseHistorico = toAnamneseHistorico(progressoMedico);
        anamneseHistorico.setAnamnese(existingAnamnese);
        anamneseHistorico.setProgressoMedico(progressoMedico.getProgressoMedico());
        anamneseHistorico.setDataAtualizacao(LocalDate.now());

        existingAnamnese.getHistoricoProgressoMedico().add(anamneseHistorico);
        anamneseRepository.save(existingAnamnese);

        anamneseHistoricoRepository.save(anamneseHistorico);
        return progressoMedico;
    }


    @Transactional
    public AnamnesePerguntaDTO addQuestionAnswerToAnamnese(Long anamneseId, AnamnesePerguntaDTO request) {

        Anamnese anamnese = anamneseRepository.findById(anamneseId).orElse(null);

        if (anamnese == null) {
            throw new IllegalArgumentException("Nenhuma anamnese encontrada com o ID: " + anamneseId);
        }

        AnamnesePergunta anamnesePergunta = toAnamnesePergunta(request);
        anamnesePergunta.setAnamnese(anamnese);
        anamnesePergunta.setResposta(request.getResposta());

        anamnese.getAnamnesePerguntas().add(anamnesePergunta);
        anamneseRepository.save(anamnese);

        anamnesePerguntaRepository.save(anamnesePergunta);
        return request;

    }


    public void delete(Long id) {
        AnamneseDTO anamneseDTO = getById(id);
        anamneseDTO.setDeletedAt(LocalDateTime.now());
        anamneseRepository.save(toAnamnese(anamneseDTO));
    }

}
