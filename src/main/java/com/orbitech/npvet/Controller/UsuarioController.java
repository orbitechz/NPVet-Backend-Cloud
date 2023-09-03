package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.UsuarioDTO;
import com.orbitech.npvet.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO>geById(@PathVariable("id") final Long id) throws Exception {
        return ResponseEntity.ok(service.GetByID(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>>getAll(){
        return ResponseEntity.ok(service.GetAll());
    }

}
