package com.orbitech.npvet.ServiceTest;

import com.orbitech.npvet.controller.UsuarioController;
import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.TipoUsuario;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.UsuarioRepository;
import com.orbitech.npvet.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@SpringBootTest
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private UsuarioController controller;

    @InjectMocks
    private UsuarioService service;

    private Usuario usuarioEntidade = new Usuario();
    private UsuarioDTO usuarioDTO = new UsuarioDTO();
    private List<Usuario> usuarioList = new ArrayList<>();
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
    void getById() throws Exception {
        UsuarioDTO result = service.getByID(1L);
        assertNotNull(result);
        verify(repository,times(1)).findById(1L);
    }
    @Test
    void getAll(){
        List<UsuarioDTO> result = service.getAll();
        assertNotNull(result);
        verify(repository,times(1)).findAll();
    }
    @Test
    void usuarioPostTest(){
        UsuarioDTO result = service.create(usuarioDTO);
        assertNotNull(result);
        verify(repository,times(1)).save(Mockito.any(Usuario.class));
    }
    @Test
    void usuarioPutTest(){
        UsuarioDTO result = service.update(1L,usuarioDTO);
        assertNotNull(result);
        verify(repository,times(1)).save(Mockito.any(Usuario.class));
    }
    @Test
    void usuarioDeleteTest(){
       service.delete(1L);
       verify(repository,times(1)).findById(1L);
    }
}
