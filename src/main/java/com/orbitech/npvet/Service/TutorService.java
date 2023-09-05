package com.orbitech.npvet.Service;

import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Entity.Tutor;
import com.orbitech.npvet.Repository.TutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    public TutorDTO toTutorDTO(Tutor tutor){
        return mapper.map(tutor, TutorDTO.class);
    }

    public Tutor toTutor(TutorDTO tutorDTO){
        return mapper.map(tutorDTO, Tutor.class);
    }

    public TutorDTO getById(Long id){
        Tutor tutorById = repository.findById(id).orElse(null);
        Assert.notNull(tutorById, String.format("Tutor com ID %s não existe!", id));
        return toTutorDTO(tutorById);
    }

    public TutorDTO getByCpf(String cpf){
        Tutor tutorByCpf = repository.findByCpf(cpf);
        Assert.notNull(tutorByCpf, String.format("Tutor com CPF %s não existe!", cpf));
        return toTutorDTO(tutorByCpf);
    }
    public TutorDTO getByRg(String rg){
        Tutor tutorByRg = repository.findByRg(rg);
        Assert.notNull(tutorByRg, String.format("Tutor com RG %s não existe!", rg));
        return toTutorDTO(tutorByRg);
    }
    public List<TutorDTO> getAllByNome(String nome){
        return repository.findAllByNomeLike(nome).stream().map(this::toTutorDTO).toList();
    }
    public List<TutorDTO> getAllByEmail(String email){
        return repository.findAllByEmailLike(email).stream().map(this::toTutorDTO).toList();
    }

    public boolean cpfExiste(String cpf){
        return repository.findByCpf(cpf) != null;
    }
    public boolean rgExiste(String rg){
        return repository.findByRg(rg) != null;
    }

    @Transactional
    public TutorDTO create(TutorDTO tutorDTO){
        Assert.isTrue(!cpfExiste(tutorDTO.getCpf()), String.format("O CPF %s já existe!", tutorDTO.getCpf()));
        Assert.isTrue(!rgExiste(tutorDTO.getRg()), String.format("O RG %s já existe!", tutorDTO.getRg()));
        Assert.notNull(tutorDTO.getEnderecos(), "Pelo menos um endereço deve ser informado!");
        Assert.notEmpty(tutorDTO.getEnderecos(), "Pelo menos um endereço deve ser informado!");
        Assert.notNull(tutorDTO.getTelefones(), "Pelo menos um telefone deve ser informado!");
        Assert.notEmpty(tutorDTO.getEnderecos(), "Pelo menos um telefone deve ser informado!");
        return toTutorDTO(repository.save(toTutor(tutorDTO)));
    }

    @Transactional
    public TutorDTO update(Long id, TutorDTO tutorDTO){
        getById(id);
        Assert.isTrue(id.equals(tutorDTO.getId()), "O Tutor informado não é o mesmo a ser atualizado!");
        String cpf = tutorDTO.getCpf();
        String rg = tutorDTO.getCpf();

        if(cpfExiste(cpf)){
            Assert.isTrue(getByCpf(cpf).getId().equals(id), String.format("CPF %s já existe!", tutorDTO.getCpf()));
        }
        if(rgExiste(rg)){
            Assert.isTrue(getByRg(rg).getId().equals(id), String.format("RG %s já existe!", tutorDTO.getRg()));
        }
        return toTutorDTO(repository.save(toTutor(tutorDTO)));
    }

    @Transactional
    public void delete(Long id){
        TutorDTO tutorById = getById(id);
        tutorById.setDeletedAt(LocalDateTime.now());
        repository.save(toTutor(tutorById));
    }

}
