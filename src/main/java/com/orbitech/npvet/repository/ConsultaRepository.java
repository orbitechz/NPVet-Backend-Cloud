package com.orbitech.npvet.repository;


import com.orbitech.npvet.entity.Consulta;
import com.orbitech.npvet.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT c FROM Consulta c  WHERE c.animal.nome LIKE :nome")
    List<Consulta>findConsultaByAnimalName(@Param("nome")String nome);

    @Query("SELECT c FROM Consulta c WHERE c.animal.id = :id")
    List<Consulta>findConsultaByAnimalId(@Param("id")Long id);

    @Query("SELECT c FROM Consulta c WHERE c.veterinario.nome LIKE :nome")
    List<Consulta>findConsultaByVeterinarioNome(@Param("nome")String nome);

    @Query("SELECT c FROM Consulta c WHERE c.veterinario.id = :id")
    List<Consulta>findConsultaByVeterinarioId(@Param("id")Long id);

    @Query("SELECT c FROM Consulta c WHERE c.anamnese.id = :id")
    List<Consulta>findConsultaByAnamneseId(@Param("id")Long id);

    @Query("SELECT c FROM Consulta c WHERE c.status =: status")
    List<Consulta>findConsultasByStatus(@Param("status")Status status);

    @Query("SELECT c FROM Consulta c WHERE " +
            "c.createdAt >= :startDate AND " +
            "c.createdAt <= :endDate " +
            "AND (:animalId IS NULL OR c.animal.id = :animalId) " +
            "AND (:vetId IS NULL OR c.veterinario.id = :vetid) " +
            "AND (:status IS NULL OR c.status = :status)")
    List<Consulta> findFilteredConsultas(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("animalId") Long animalId,
            @Param("vetId") Long vetId,
            @Param("status") Status status);






}
