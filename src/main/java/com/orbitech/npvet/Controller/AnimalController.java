package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<AnimalDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnimalDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<AnimalDTO>> getByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getAllByNome(nome));
    }

    @GetMapping("/raca/{raca}")
    public ResponseEntity<List<AnimalDTO>> getByRaca(@PathVariable("raca") String raca) {
        return ResponseEntity.ok(service.getAllByRaca(raca));
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<AnimalDTO>> getByEspecie(@PathVariable("especie") String especie) {
        return ResponseEntity.ok(service.getAllByEspecie(especie));
    }

    @PostMapping("/post")
    public ResponseEntity<AnimalDTO> create(@RequestBody @Validated AnimalDTO AnimalDTO){
        return ResponseEntity.ok(service.create(AnimalDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AnimalDTO> update(@PathVariable("id") Long id, @RequestBody @Validated AnimalDTO animalDTO){
        return ResponseEntity.ok(service.update(id, animalDTO));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("Animal %s desativado com sucesso!", id));
    }

}
