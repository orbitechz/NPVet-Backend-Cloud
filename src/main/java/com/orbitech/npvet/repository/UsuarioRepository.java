package com.orbitech.npvet.repository;

import com.orbitech.npvet.entity.Role;
import com.orbitech.npvet.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT c FROM Usuario c WHERE c.nome like :nome")
    List<Usuario>findAllUsuariosByNome(@Param("nome")String nome);

    @Query("SELECT c FROM Usuario c WHERE c.role = :tipoUsuario")
    List<Usuario>findByTipoUsuario(@Param("tipoUsuario") Role role);

    @Query("SELECT c FROM Usuario c WHERE c.username = :username")
    List<Usuario>findUsuarioByUsername(@Param("username")String username);

    @Query("SELECT c FROM Usuario c WHERE c.cpf = :cpf")
    Usuario findUsuarioByCpf(@Param("cpf")String cpf);

}
