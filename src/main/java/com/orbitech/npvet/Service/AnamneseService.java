package com.orbitech.npvet.Service;
import com.orbitech.npvet.DTO.*;
import com.orbitech.npvet.Entity.*;
import com.orbitech.npvet.Repository.*;
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
    private final AnimalRepository animalRepository;
    private final TutorRepository tutorRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AnamneseService(AnamneseRepository anamneseRepository,
                           PerguntaRepository perguntaRepository,
                           AnimalRepository animalRepository,
                           TutorRepository tutorRepository,
                           UsuarioRepository usuarioRepository,
                           ModelMapper modelMapper) {
        this.anamneseRepository = anamneseRepository;
        this.perguntaRepository = perguntaRepository;
        this.animalRepository = animalRepository;
        this.tutorRepository = tutorRepository;
        this.usuarioRepository = usuarioRepository;
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

        if (anamnese.getUsuario() != null) {
            UsuarioDTO usuarioDTO = modelMapper.map(anamnese.getUsuario(), UsuarioDTO.class);
            anamneseDTO.setUsuarioDTO(usuarioDTO);
        }

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

        if (anamneseDTO.getUsuarioDTO() != null) {
            Usuario usuario = usuarioRepository.findById(anamneseDTO.getUsuarioDTO().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " +
                            anamneseDTO.getUsuarioDTO().getId()));
            anamnese.setUsuario(usuario);
        }

        return anamnese;
    }

}
