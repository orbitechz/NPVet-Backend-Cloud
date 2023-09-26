package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.UsuarioDTO;
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
    public ResponseEntity<UsuarioDTO>geById(@PathVariable("id") final Long id) throws Exception {
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

}
