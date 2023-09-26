package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.PerguntaDTO;
import com.orbitech.npvet.entity.Pergunta;
import com.orbitech.npvet.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @Autowired
    public PerguntaService perguntaService;

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(perguntaService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PerguntaDTO>> getAll() {
        return ResponseEntity.ok(perguntaService.getAll());
    }

    @PostMapping("/post")
    public ResponseEntity<String> create(@RequestBody @Validated PerguntaDTO perguntaDTO) {
        try {
            Pergunta pergunta = perguntaService.create(perguntaDTO);
            return ResponseEntity.ok("O registro da pergunta foi realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Ocorreu um erro durante o cadastro: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @RequestBody @Validated PerguntaDTO perguntaDTO) {
        try {
            Pergunta pergunta = perguntaService.update(id, perguntaDTO);
            return ResponseEntity.ok("O registro da pergunta foi atualizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Ocorreu um erro durante a atualização: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.perguntaService.delete(id);
        return ResponseEntity.ok().body("Pergunta excluída com sucesso!");
    }


}
