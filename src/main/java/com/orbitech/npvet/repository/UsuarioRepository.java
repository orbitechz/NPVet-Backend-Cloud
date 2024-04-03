package com.orbitech.npvet.repository;

import com.orbitech.npvet.entity.Role;
import com.orbitech.npvet.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    @Query("SELECT c FROM Usuario c WHERE c.nome like :nome")
    List<Usuario>findAllUsuariosByNome(@Param("nome")String nome);

    @Query("SELECT c FROM Usuario c WHERE c.role = :role")
    List<Usuario> findByRole(@Param("role") Role role);

    Optional<Usuario> findUsuarioByUsername(String username);

    @Query("SELECT c FROM Usuario c WHERE c.cpf = :cpf")
    Usuario findUsuarioByCpf(@Param("cpf")String cpf);

}
