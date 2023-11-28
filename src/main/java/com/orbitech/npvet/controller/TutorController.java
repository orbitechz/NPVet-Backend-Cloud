package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.TutorDTO;
import com.orbitech.npvet.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class TutorController {
    @Autowired
    private TutorService service;
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO')")
    public ResponseEntity<TutorDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/nome/{nome}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO')")
    public ResponseEntity<List<TutorDTO>> getByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getAllByNome(nome));
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO')")
    public ResponseEntity<List<TutorDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/all/ativos")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO')")
    public ResponseEntity<List<TutorDTO>> getAllAtivados() {
        return ResponseEntity.ok(service.getAllAtivados());
    }
    @GetMapping("/all/desativados")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO')")
    public ResponseEntity<List<TutorDTO>> getAllDesativados() {
        return ResponseEntity.ok(service.getAllDesativados());
    }
    @GetMapping("/cpf/{cpf}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO')")
    public ResponseEntity<TutorDTO> getByCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(service.getByCpf(cpf));
    }
    @GetMapping("/rg/{rg}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO')")
    public ResponseEntity<TutorDTO> getByRg(@PathVariable("rg") String rg) {
        return ResponseEntity.ok(service.getByRg(rg));
    }
    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('SECRETARIA')")
    public ResponseEntity<TutorDTO> create(@RequestBody @Validated TutorDTO tutorDTO){
        return ResponseEntity.ok(service.create(tutorDTO));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA')")
    public ResponseEntity<TutorDTO> update(@PathVariable("id") Long id, @RequestBody @Validated TutorDTO tutorDTO){
        return ResponseEntity.ok(service.update(id, tutorDTO));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<TutorDTO> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }
    @PostMapping("/activate/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<TutorDTO> activate(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.activate(id));
    }
}
