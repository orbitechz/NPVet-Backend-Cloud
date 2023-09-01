package com.orbitech.npvet.Repository;

import com.orbitech.npvet.Entity.Anamnese;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AnamneseRepository extends JpaRepository<Anamnese,Long> {
    List<Anamnese> findByTutorCpf();

    List<Anamnese> findByTutorCpfAndAnimal(String cpf, String nome);
}
