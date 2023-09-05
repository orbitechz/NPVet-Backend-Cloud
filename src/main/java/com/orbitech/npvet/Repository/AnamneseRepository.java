package com.orbitech.npvet.Repository;

import com.orbitech.npvet.Entity.Anamnese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface AnamneseRepository extends JpaRepository<Anamnese,Long> {
    @Query("SELECT a FROM Anamnese a JOIN a.tutor t WHERE t.cpf = :cpf")
    List<Anamnese> findByTutorCpf(@Param("cpf") String cpf);

    @Query("SELECT a FROM Anamnese a JOIN a.tutor t JOIN a.animal an WHERE t.cpf = :cpf AND an.nome = :nome")
    List<Anamnese> findByTutorCpfAndAnimal(@Param("cpf") String cpf, @Param("nome") String nome);
}
