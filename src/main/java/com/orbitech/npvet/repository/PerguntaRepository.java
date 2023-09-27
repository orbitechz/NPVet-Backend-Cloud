package com.orbitech.npvet.repository;

import com.orbitech.npvet.entity.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PerguntaRepository extends JpaRepository<Pergunta,Long> {
    @Query("SELECT p FROM Pergunta p WHERE p.enunciado = :enunciado")
    Pergunta findByEnunciado(@Param("enunciado") String enunciado);

    @Query("SELECT COUNT(p) > 0 FROM Pergunta p WHERE p.enunciado = :enunciado")
    boolean existsByEnunciado(@Param("enunciado") String enunciado);
}
