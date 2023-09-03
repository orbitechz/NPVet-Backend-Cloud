package com.orbitech.npvet.Repository;

import com.orbitech.npvet.Entity.ExameFisico;
import com.orbitech.npvet.Entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExameFisicoRepository extends JpaRepository<ExameFisico, Long> {

}
