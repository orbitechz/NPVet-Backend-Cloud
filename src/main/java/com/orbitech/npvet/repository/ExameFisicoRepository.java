package com.orbitech.npvet.repository;

import com.orbitech.npvet.entity.ExameFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExameFisicoRepository extends JpaRepository<ExameFisico, Long> {

    @Query("from ExameFisico where animal.nome like:nome")
    public List<ExameFisico> findAllByNomeLike(@Param("nome") String nome);


    @Query("from ExameFisico where animal.id = id")
    public List<ExameFisico> findAllById(@Param("id") Long id);

}
