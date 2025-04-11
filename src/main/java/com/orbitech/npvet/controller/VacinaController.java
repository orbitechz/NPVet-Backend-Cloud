package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.dto.VacinaDTO;
import com.orbitech.npvet.service.VacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/npvet-api/vacina")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")

public class VacinaController {
    @Autowired
    private VacinaService service;

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<VacinaDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'SECRETARIA', 'ADMINISTRADOR')")
    public ResponseEntity<VacinaDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/nome/{nome}")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'SECRETARIA', 'ADMINISTRADOR')")
    public ResponseEntity<List<VacinaDTO>> getByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getAllByNome(nome));
    }
    @GetMapping("/animal/{animalId}")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'SECRETARIA', 'ADMINISTRADOR')")
    public ResponseEntity<List<VacinaDTO>> getByAnimal(@PathVariable("animalId") Long animalId) {
        return ResponseEntity.ok(service.getByAnimal(animalId));
    }
    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<VacinaDTO> create(@RequestBody @Validated VacinaDTO vacinaDTO){
        return ResponseEntity.ok(service.create(vacinaDTO));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<VacinaDTO> update(@PathVariable("id") Long id, @RequestBody @Validated VacinaDTO vacinaDTO){
        return ResponseEntity.ok(service.update(id, vacinaDTO));
    }
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("Vacina %s deletada com sucesso!", id));
    }

}
