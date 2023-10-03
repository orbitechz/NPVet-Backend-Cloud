package com.orbitech.npvet.repository;

import com.orbitech.npvet.entity.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
    @Query("from Vacina where nome like :nome")
    List<Vacina> findAllByNomeLike(@Param("nome") String nome);

    @Query("from Vacina where animal.id = :animalId")
    List<Vacina> findAllByAnimalId(@Param("animalId") Long animalId);}
