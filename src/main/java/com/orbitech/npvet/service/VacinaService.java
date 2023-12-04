package com.orbitech.npvet.service;

import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.VacinaDTO;
import com.orbitech.npvet.entity.Vacina;
import com.orbitech.npvet.repository.VacinaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class VacinaService {
    @Autowired
    private VacinaRepository repository;
    @Autowired
    private AnimalService animalService;
    private final ModelMapper mapper = new ModelMapper();
    public VacinaDTO toVacinaDTO(Vacina vacina){
        return mapper.map(vacina, VacinaDTO.class);
    }

    public Vacina toVacina(VacinaDTO vacinaDTO){
        return mapper.map(vacinaDTO, Vacina.class);
    }

    public List<VacinaDTO> getAll() {
        return repository.findAll().stream().map(this::toVacinaDTO).toList();
    }

    public VacinaDTO getById(Long id) {
        Vacina vacinaById = repository.findById(id).orElse(null);
        Assert.notNull(vacinaById, String.format("Vacina com ID %s não existe!", id));
        return toVacinaDTO(vacinaById);
    }

    public List<VacinaDTO> getAllByNome(String nome) {
        return repository.findAllByNomeLike(nome).stream().map(this::toVacinaDTO).toList();
    }

    public List<VacinaDTO> getByAnimal(Long animalId) {
        return repository.findAllByAnimalId(animalId).stream().map(this::toVacinaDTO).toList();
    }
    @Transactional
    public VacinaDTO create(VacinaDTO vacinaDTO) {
        animalService.getById(vacinaDTO.getAnimal().getId());
        return toVacinaDTO(repository.save(toVacina(vacinaDTO)));
    }

    @Transactional
    public VacinaDTO update(Long id, VacinaDTO vacinaDTO){
        getById(id);
        animalService.getById(vacinaDTO.getAnimal().getId());
        return toVacinaDTO(repository.save(toVacina(vacinaDTO)));
    }
    @Transactional
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }
}
