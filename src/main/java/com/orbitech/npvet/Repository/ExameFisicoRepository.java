package com.orbitech.npvet.Repository;

import com.orbitech.npvet.Entity.Animal;
import com.orbitech.npvet.Entity.ExameFisico;
import com.orbitech.npvet.Entity.Tutor;
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
