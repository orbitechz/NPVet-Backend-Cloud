package com.orbitech.npvet.repository;

import com.orbitech.npvet.entity.AnamneseHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnamneseHistoricoRepository extends JpaRepository<AnamneseHistorico,Long> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AnamneseHistorico a WHERE a.progressoMedico = :progressoMedico")
    boolean existsByProgressoMedico(@Param("progressoMedico") String progressoMedico);
}
