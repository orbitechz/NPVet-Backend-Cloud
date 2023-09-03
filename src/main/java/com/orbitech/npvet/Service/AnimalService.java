package com.orbitech.npvet.Service;

import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Entity.Animal;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Repository.AnimalRepository;
import com.orbitech.npvet.Repository.TutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class AnimalService {

    private final ModelMapper mapper = new ModelMapper();

    private AnimalRepository repository;

    public AnimalDTO toAnimalDTO(Animal animal){
        return mapper.map(animal, AnimalDTO.class);
    }

    public Animal toAnimal(AnimalDTO animalDTO){ return mapper.map(animalDTO, Animal.class); }

    public AnimalDTO getById(Long id){
        Animal animalById = repository.findById(id).orElse(null);
        Assert.notNull(animalById, String.format("Animal com ID %s não existe!", id));
        return toAnimalDTO(animalById);
    }

    public List<AnimalDTO> getAllByNome(String nome){
        return repository.findAllByNomeLike(nome).stream().map(this::toAnimalDTO).toList();
    }

    public List<AnimalDTO> getAllByRaca(String raca){
        return repository.findAllByRacaLike(raca).stream().map(this::toAnimalDTO).toList();
    }



}
