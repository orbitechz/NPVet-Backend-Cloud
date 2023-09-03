package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.UsuarioDTO;
import com.orbitech.npvet.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UsuarioDTO>create(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(service.create(usuarioDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioDTO>update(@PathVariable("id") final long id, UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(service.update(id,usuarioDTO));
    }



}
