package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.Controller.UsuarioController;
import com.orbitech.npvet.DTO.UsuarioDTO;
import com.orbitech.npvet.Entity.TipoUsuario;
import com.orbitech.npvet.Entity.Usuario;
import com.orbitech.npvet.Repository.UsuarioRepository;
import com.orbitech.npvet.Service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioControllerTest {

    @MockBean
    private UsuarioRepository repository;

    @Autowired
    private UsuarioController controller;

    @Autowired
    private UsuarioService service;

    private Usuario usuarioEntidade = new Usuario();
    private UsuarioDTO usuarioDTO = new UsuarioDTO();
    private List<Usuario>usuarioList = new ArrayList<>();
    private List<UsuarioDTO>usuarioDTOList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        usuarioDTO.setId(1L);
        usuarioDTO.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
        usuarioDTO.setNome("nome");
        usuarioDTO.setCpf("cpf");
        usuarioDTO.setSenha("senha");
        usuarioDTO.setUsername("username");
        usuarioDTOList.add(usuarioDTO);

        usuarioEntidade.setId(1L);
        usuarioEntidade.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
        usuarioEntidade.setNome("nome");
        usuarioEntidade.setCpf("cpf");
        usuarioEntidade.setSenha("senha");
        usuarioEntidade.setUsername("username");
        usuarioList.add(usuarioEntidade);

        when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(usuarioEntidade));
        when(repository.findAll()).thenReturn(usuarioList);
        when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuarioEntidade);

    }

    @Test
    void getById() throws Exception{
        ResponseEntity<UsuarioDTO>response = controller.geById(1L);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(usuarioDTO);
    }
    @Test
    void getAll(){
        ResponseEntity<List<UsuarioDTO>>response = controller.getAll();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(usuarioDTOList);
    }


}
