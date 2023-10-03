package com.orbitech.npvet.EntityTest;

import com.orbitech.npvet.entity.TipoUsuario;
import com.orbitech.npvet.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UsuarioTest {

    Usuario usuario = new Usuario();

    @BeforeEach
    void setUp(){
        usuario.setTipoUsuario(TipoUsuario.SECRETARIA);
        usuario.setNome("nome");
        usuario.setSenha("senha");
        usuario.setId(1L);
        usuario.setUsername("username");
        usuario.setCpf("cpf");
    }

    @Test
    void usuarioIdTest(){
        assertEquals(1L,usuario.getId());
    }

    @Test
    void usuarioNomeTest(){
        assertEquals("nome",usuario.getNome());
    }

    @Test
    void usuarioTipoTest(){
        assertEquals(TipoUsuario.SECRETARIA,usuario.getTipoUsuario());
    }

    @Test
    void usuarioSenhaTest(){
        assertEquals("senha",usuario.getSenha());
    }

    @Test
    void usuarioUsernameTest(){
        assertEquals("username",usuario.getUsername());
    }

    @Test
    void usuarioCpfTest(){
        assertEquals("cpf",usuario.getCpf());
    }

}
