package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

@GetMapping("/{id}")
public ResponseEntity<UsuarioDTO>geById(@PathVariable("id") final Long id){
    return null;
}



}
