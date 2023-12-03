package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.controller.UsuarioController;
import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.Role;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.UsuarioRepository;
import com.orbitech.npvet.service.UsuarioService;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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

//        usuarioDTO.setId(1L);
        usuarioDTO.setRole(Role.ADMINISTRADOR);
        usuarioDTO.setNome("nome");
        usuarioDTO.setCpf("cpf");
//        usuarioDTO.setSenha("senha");
        usuarioDTO.setUsername("username");
        usuarioDTOList.add(usuarioDTO);

//        usuarioEntidade.setId(1L);
        usuarioEntidade.setRole(Role.ADMINISTRADOR);
        usuarioEntidade.setNome("nome");
        usuarioEntidade.setCpf("cpf");
//        usuarioEntidade.setSenha("senha");
        usuarioEntidade.setUsername("username");
        usuarioList.add(usuarioEntidade);

        when(repository.findById(Mockito.any(String.class))).thenReturn(Optional.of(usuarioEntidade));
        when(repository.findAll()).thenReturn(usuarioList);
        when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuarioEntidade);

        when(repository.findAllUsuariosByNome("nome")).thenReturn(usuarioList);
        when(repository.findByRole(Role.SECRETARIA)).thenReturn(usuarioList);
        when(repository.findByRole(Role.ADMINISTRADOR)).thenReturn(usuarioList);
        when(repository.findByRole(Role.MEDICO)).thenReturn(usuarioList);

        when(repository.findUsuarioByUsername("username")).thenReturn(usuarioList);
        when(repository.findUsuarioByCpf("cpf")).thenReturn(usuarioEntidade);


    }

    @Test
    void getById() throws Exception{
        ResponseEntity<UsuarioDTO>response = controller.geById("1");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(usuarioDTO);
    }
    @Test
    void getAll(){
        ResponseEntity<List<UsuarioDTO>>response = controller.getAll();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(usuarioDTOList);
    }
//    @Test
//    void usuarioPostTest(){
//        ResponseEntity<UsuarioDTO>response = controller.create(usuarioDTO);
//        assertEquals(HttpStatus.OK,response.getStatusCode());
//        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(usuarioDTO);
//    }
    @Test
    void usuarioPutTest(){
        ResponseEntity<UsuarioDTO>response = controller.update(1L,usuarioDTO);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(usuarioDTO);
    }
//    @Test
//    void usuarioDeleteTest(){
//        ResponseEntity<String>response = controller.delete(1L);
//        assertEquals(HttpStatus.OK,response.getStatusCode());
//        assertEquals("Usuário com id [1] deletado.", response.getBody());
//    }

    @Test
    void getUserByNameTest(){
        ResponseEntity<List<UsuarioDTO>>response = controller.getUsuarioByName("nome");
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(usuarioDTOList);
    }

    @Test
    void getTipoSecretaria(){

       ResponseEntity<List<UsuarioDTO>>response = controller.getSecretaria();
       assertNotNull(response);
       assertEquals(HttpStatus.OK,response.getStatusCode());
       assertThat(response.getBody().get(0)).usingRecursiveComparison().isEqualTo(usuarioDTO);
    }

    @Test
    void getTipoAdm(){
        ResponseEntity<List<UsuarioDTO>>response = controller.getAdm();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody().get(0)).usingRecursiveComparison().isEqualTo(usuarioDTO);
    }

    @Test
    void getTipoMedico(){
        ResponseEntity<List<UsuarioDTO>>response = controller.getVeterinarios();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody().get(0)).usingRecursiveComparison().isEqualTo(usuarioDTO);
    }

    @Test
    void getByUsername(){
        ResponseEntity<List<UsuarioDTO>>response = controller.getUsername("username");
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(usuarioDTOList);
    }

//    @Test
//    void getByCpf(){
//        ResponseEntity<UsuarioDTO>response = controller.getUsuarioCpf("cpf");
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK,response.getStatusCode());
//        assertThat(response).usingRecursiveComparison().isEqualTo(usuarioDTO);
//    }
}
