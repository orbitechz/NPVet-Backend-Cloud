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
import java.util.ArrayList;
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
    private final ModelMapper modelMapper;

    @Autowired
    public AnamneseService(AnamneseRepository anamneseRepository,
                           AnamnesePerguntaRepository anamnesePerguntaRepository,
                           AnamneseHistoricoRepository anamneseHistoricoRepository,
                           PerguntaRepository perguntaRepository,
                           AnimalRepository animalRepository,
                           TutorRepository tutorRepository,
                           ModelMapper modelMapper) {
        this.anamneseRepository = anamneseRepository;
        this.anamnesePerguntaRepository = anamnesePerguntaRepository;
        this.anamneseHistoricoRepository = anamneseHistoricoRepository;
        this.perguntaRepository = perguntaRepository;
        this.animalRepository = animalRepository;
        this.tutorRepository = tutorRepository;
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


    private AnamneseHistorico toAnamneseHistorico(AnamneseHistoricoDTO progressoMedico) {
        AnamneseHistorico anamneseHistorico = modelMapper.map(progressoMedico, AnamneseHistorico.class);

        Anamnese anamnese = anamneseRepository.findById(progressoMedico.getAnamnese().getId()).orElse(null);
        if (anamnese == null) {
            throw new IllegalArgumentException("Anamnese não encontrada com o ID: "
                    + progressoMedico.getAnamnese().getId());
        }

        anamneseHistorico.setAnamnese(anamnese);

        return anamneseHistorico;
    }

    private AnamnesePergunta toAnamnesePergunta(AnamnesePerguntaDTO request) {

        AnamnesePergunta anamnesePergunta = modelMapper.map(request, AnamnesePergunta.class);

        Anamnese anamnese = anamneseRepository.findById(request.getAnamneseDTO().getId()).orElse(null);
        if (anamnese == null) {
            throw new IllegalArgumentException("Anamnese não encontrada com o ID: "
                    + request.getAnamneseDTO().getId());
        }

        Pergunta pergunta = perguntaRepository.findById(request.getPerguntaDTO().getId()).orElse(null);
        if (pergunta == null) {
            throw new IllegalArgumentException("Pergunta não encontrada com o ID: "
                    + request.getPerguntaDTO().getId());
        }

        anamnesePergunta.setAnamnese(anamnese);
        anamnesePergunta.setPergunta(pergunta);

        return anamnesePergunta;
    }


    public AnamneseDTO toAnamneseDTO(Anamnese anamnese){
        AnamneseDTO anamneseDTO = modelMapper.map(anamnese, AnamneseDTO.class);

        if (anamnese.getAnimal() != null) {
            AnimalDTO animalDTO = modelMapper.map(anamnese.getAnimal(), AnimalDTO.class);
            anamneseDTO.setAnimalDTO(animalDTO);
        }

        if (anamnese.getTutor() != null) {
            TutorDTO tutorDTO = modelMapper.map(anamnese.getTutor(), TutorDTO.class);
            anamneseDTO.setTutorDTO(tutorDTO);
        }

        if (anamnese.getVeterinario() != null) {
            UsuarioDTO usuarioDTO = modelMapper.map(anamnese.getVeterinario(), UsuarioDTO.class);
            anamneseDTO.setVeterinarioDTO(usuarioDTO);
        }

        List<AnamnesePerguntaDTO> anamnesePerguntaDTOs = new ArrayList<>();
        for (AnamnesePergunta anamnesePergunta : anamnese.getAnamnesePerguntas()) {
            AnamnesePerguntaDTO perguntaDTO = modelMapper.map(anamnesePergunta, AnamnesePerguntaDTO.class);

            if (anamnesePergunta.getAnamnese() != null) {
                AnamneseDTO anamnesePerguntaDTO = modelMapper.map(anamnesePergunta.getAnamnese(), AnamneseDTO.class);
                perguntaDTO.setAnamneseDTO(anamnesePerguntaDTO);
            }

            if (anamnesePergunta.getPergunta() != null) {
                PerguntaDTO perguntaPerguntaDTO = modelMapper.map(anamnesePergunta.getPergunta(), PerguntaDTO.class);
                perguntaDTO.setPerguntaDTO(perguntaPerguntaDTO);
            }

            anamnesePerguntaDTOs.add(perguntaDTO);
        }
        anamneseDTO.setAnamnesePerguntas(anamnesePerguntaDTOs);

        // Mapear o histórico de progresso médico
        List<AnamneseHistoricoDTO> historicoDTOs = new ArrayList<>();
        for (AnamneseHistorico historico : anamnese.getHistoricoProgressoMedico()) {
            AnamneseHistoricoDTO historicoDTO = modelMapper.map(historico, AnamneseHistoricoDTO.class);
            historicoDTOs.add(historicoDTO);
        }
        anamneseDTO.setHistoricoProgressoMedico(historicoDTOs);

        return anamneseDTO;
    }

    public Anamnese toAnamnese(AnamneseDTO anamneseDTO) {
        Anamnese anamnese = modelMapper.map(anamneseDTO, Anamnese.class);

        // Mapear entidades associadas de DTOs para entidades
        if (anamneseDTO.getAnimalDTO() != null) {
            Animal animal = animalRepository.findById(anamneseDTO.getAnimalDTO().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado com o ID: " +
                            anamneseDTO.getAnimalDTO().getId()));
            anamnese.setAnimal(animal);
        }

        if (anamneseDTO.getTutorDTO() != null) {
            Tutor tutor = tutorRepository.findById(anamneseDTO.getTutorDTO().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Tutor não encontrado com o ID: " +
                            anamneseDTO.getTutorDTO().getId()));
            anamnese.setTutor(tutor);
        }

        List<AnamnesePergunta> anamnesePergunta = new ArrayList<>();
        for (AnamnesePerguntaDTO anamnesePerguntadto : anamneseDTO.getAnamnesePerguntas()) {
            AnamnesePergunta pergunta = modelMapper.map(anamnesePerguntadto, AnamnesePergunta.class);


            if (anamnesePerguntadto.getAnamneseDTO() != null) {
                Anamnese anamnese1 = modelMapper.map(anamnesePerguntadto.getAnamneseDTO(), Anamnese.class);
                pergunta.setAnamnese(anamnese1);
            }

            if (anamnesePerguntadto.getPerguntaDTO() != null) {
                Pergunta pergunta1 = modelMapper.map(anamnesePerguntadto.getPerguntaDTO(), Pergunta.class);
                pergunta.setPergunta(pergunta1);
            }

            anamnesePergunta.add(pergunta);
        }
        anamnese.setAnamnesePerguntas(anamnesePergunta);

        return anamnese;
    }

}
