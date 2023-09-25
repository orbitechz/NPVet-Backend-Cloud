package com.orbitech.npvet.Repository;

import com.orbitech.npvet.Entity.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
    @Query("from Vacina where nome like :nome")
    List<Vacina> findAllByNomeLike(@Param("nome") String nome);

    @Query("from Vacina where animal.id like :nome")
    List<Vacina> findAllByAnimalId(@Param("animal_id") Long animal_id);}
