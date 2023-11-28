package com.orbitech.npvet.auth;

import com.orbitech.npvet.dto.LoginDTO;
import com.orbitech.npvet.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.InvalidKeyException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService service;
    @PostMapping
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO login) throws AccessDeniedException, InvalidKeyException {
        return ResponseEntity.ok(service.login(login));
    }
    @GetMapping("logout")
    public ResponseEntity<HttpStatus> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(null, HttpStatus.OK);

    }
}
