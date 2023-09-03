package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.DTO.PerguntaDTO;
import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<AnimalDTO>> getByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getAllByNome(nome));
    }

    @GetMapping("/raca/{raca}")
    public ResponseEntity<List<AnimalDTO>> getByRaca(@PathVariable("raca") String raca) {
        return ResponseEntity.ok(service.getAllByRaca(raca));
    }


}
