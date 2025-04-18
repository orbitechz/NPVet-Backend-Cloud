package com.orbitech.npvet.service;

import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.TutorDTO;
import com.orbitech.npvet.entity.Animal;
import com.orbitech.npvet.entity.Tutor;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.AnimalRepository;
import com.orbitech.npvet.repository.TutorRepository;
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
public class AnimalService {

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private AnimalRepository repository;
    @Autowired
    private TutorRepository tutorRepository;

    public AnimalDTO toAnimalDTO(Animal animal){
        return mapper.map(animal, AnimalDTO.class);
    }

    public Animal toAnimal(AnimalDTO animalDTO){return mapper.map(animalDTO, Animal.class); }

    public AnimalDTO getById(Long id){
        Animal animalById = repository.findById(id).orElse(null);
        Assert.notNull(animalById, String.format("Animal com ID %s não existe!", id));
        return toAnimalDTO(animalById);
    }

    public List<AnimalDTO> getAllAtivo(){
        return repository.getAllAtivados().stream().map(this::toAnimalDTO).toList();
    }

    public List<AnimalDTO> getAllByTutor(Long tutorId) {
        return repository.getAllByTutor(tutorId).stream().map(this::toAnimalDTO).toList();
    }

    public AnimalDTO getByTutorAndName(Long tutorId, String nome) {
        return toAnimalDTO(repository.getByTutorAndName(tutorId,nome));
    }

    public List<AnimalDTO> getAllDesativado(){
        return repository.getAllDesativados().stream().map(this::toAnimalDTO).toList();
    }

    public List<AnimalDTO> getAll() {
        return repository.findAll().stream().map(this::toAnimalDTO).toList();
    }

    public List<AnimalDTO> getAllByNome(String nome){
        return repository.findAllByNomeLike(nome).stream().map(this::toAnimalDTO).toList();
    }

    public List<AnimalDTO> getAllByRaca(String raca){
        return repository.findAllByRacaLike(raca).stream().map(this::toAnimalDTO).toList();
    }

    public List<AnimalDTO> getAllByEspecie(String especie){
        return repository.findAllByEspecieLike(especie).stream().map(this::toAnimalDTO).toList();
    }
    public List<AnimalDTO> getAllByTutorId(Long id){
        return repository.findAllByTutorId(id).stream().map(this::toAnimalDTO).toList();
    }

    @Transactional
    public AnimalDTO create(AnimalDTO animalDTO, Usuario usuario){
        Tutor tutorBanco = tutorRepository.findById(animalDTO.getTutorId().getId()).orElse(null);
        Assert.notNull(tutorBanco, "O tutor informado não existe!");
        AnimalDTO animal = toAnimalDTO(repository.save(toAnimal(animalDTO)));
        log.info("ANIMAL: " + animal.getId() + " RAÇA: " + animal.getRaca() + " NOME: " + animal.getNome() + " | CADASTRADO POR: " + usuario.getId() + " NOME: " + usuario.getNome());
        return animal;
    }

    @Transactional
    public AnimalDTO update(Long id, AnimalDTO animalDTO, Usuario usuario){
        Animal animalById = repository.findById(id).orElse(null);
        Assert.notNull(animalById, String.format("Animal com ID %s não existe!", id));
        Assert.isTrue(id.equals(animalDTO.getId()), "O ID da URL não é igual ao ID do body");
        Animal animalSaved = repository.save(toAnimal(animalDTO));
        log.info("ANIMAL: " + animalSaved.getId() + " RAÇA: " + animalSaved.getRaca() + " NOME: " + animalSaved.getNome() + " | ATUALIZADO POR: " + usuario.getId() + " NOME: " + usuario.getNome());
        return toAnimalDTO(animalSaved);
    }

    @Transactional
    public AnimalDTO delete(Long id, Usuario usuario){
        AnimalDTO animalDTO = getById(id);

        animalDTO.delete();
        Animal animalSaved = repository.save(toAnimal(animalDTO));
        log.info("ANIMAL: " + animalSaved.getId() + " RAÇA: " + animalSaved.getRaca() + " NOME: " + animalSaved.getNome() + " | DELETADO POR: " + usuario.getId() + " NOME: " + usuario.getNome());

        return toAnimalDTO(animalSaved);
    }

    @Transactional
    public AnimalDTO activate(Long id, Usuario usuario){
        AnimalDTO animalDTO = getById(id);
        animalDTO.activate();
        Animal animalSaved = repository.save(toAnimal(animalDTO));
        log.info("ANIMAL: " + animalSaved.getId() + " RAÇA: " + animalSaved.getRaca() + " NOME: " + animalSaved.getNome() + " | CADASTRADO POR: " + usuario.getId() + " NOME: " + usuario.getNome());
        return toAnimalDTO(animalSaved);
    }
}