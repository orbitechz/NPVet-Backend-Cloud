package com.orbitech.npvet.DTOTest;

import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class UsuarioDtoTest {

    private UsuarioDTO usuarioDTO = new UsuarioDTO();

    @BeforeEach
    void setUp(){
        usuarioDTO.setRole(Role.SECRETARIA);
        usuarioDTO.setNome("nome");
//        usuarioDTO.setSenha("senha");
//        usuarioDTO.setId(1L);
        usuarioDTO.setUsername("username");
        usuarioDTO.setCpf("cpf");
    }

    @Test
    void usuarioIdTest(){
        assertEquals(1L,usuarioDTO.getId());
    }

    @Test
    void usuarioNomeTest(){
        assertEquals("nome",usuarioDTO.getNome());
    }

    @Test
    void usuarioTipoTest(){
        assertEquals(Role.SECRETARIA,usuarioDTO.getRole());
    }

//    @Test
//    void usuarioSenhaTest(){
//        assertEquals("senha",usuarioDTO.getSenha());
//    }

    @Test
    void usuarioUsernameTest(){
        assertEquals("username",usuarioDTO.getUsername());
    }

    @Test
    void usuarioCpfTest(){
        assertEquals("cpf",usuarioDTO.getCpf());
    }

}
