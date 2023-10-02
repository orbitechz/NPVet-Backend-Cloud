package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.TipoUsuario;
import com.orbitech.npvet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO>geById(@PathVariable("id") final Long id){
        return ResponseEntity.ok(service.getByID(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping("/post")
    public ResponseEntity<UsuarioDTO>create(@Validated @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(service.create(usuarioDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioDTO>update( @PathVariable("id") final long id, @RequestBody @Validated UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(service.update(id,usuarioDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable("id") final long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("Usuário com id [%s] deletado.",id));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<UsuarioDTO>>getUsuarioByName(@PathVariable("nome") String nome){
        return ResponseEntity.ok(service.getUsuarioByName(nome));

    }

    @GetMapping("/secretaria")
    public ResponseEntity<List<UsuarioDTO>>getSecretaria(){
        List<UsuarioDTO>response = service.getTipoSecretaria();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/adm")
    public ResponseEntity<List<UsuarioDTO>>getAdm(){
        List<UsuarioDTO>response = service.getTipoAdm(TipoUsuario.ADMINISTRADOR);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/veterinarios")
    public ResponseEntity<List<UsuarioDTO>>getVeterinarios(){
        List<UsuarioDTO>response = service.getTipoMedico(TipoUsuario.MEDICO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<UsuarioDTO>>getUsername(@PathVariable("username")String username){
        return ResponseEntity.ok(service.getUsername(username));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<List<UsuarioDTO>>getUsuarioCpf(@PathVariable("cpf")String cpf){
        return ResponseEntity.ok(service.getUsuarioByCpf(cpf));
    }

}
