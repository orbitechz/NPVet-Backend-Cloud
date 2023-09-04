package com.orbitech.npvet.Repository;

import com.orbitech.npvet.Entity.Animal;
import com.orbitech.npvet.Entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal,Long> {

    @Query("from Animal where nome like:nome")
    public List<Animal> findAllByNomeLike(@Param("nome") String nome);

    @Query("from Animal where raca like:raca")
    public List<Animal> findAllByRacaLike(@Param("raca") String raca);

    @Query("from Animal where especie like:especie")
    public List<Animal> findAllByEspecieLike(@Param("especie") String especie);

}
