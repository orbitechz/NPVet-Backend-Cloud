package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.ExameFisicoDTO;
import com.orbitech.npvet.service.ExameFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/npvet-api/examefisico")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class ExameFisicoController {

    @Autowired
    private ExameFisicoService service;

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<ExameFisicoDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/animal/nome/{nome}")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ExameFisicoDTO>> getByAnimalNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getByNomeAnimal(nome));
    }

    @GetMapping("/animal/id/{id}")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ExameFisicoDTO>> getByAnimalId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getByIdAnimal(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ExameFisicoDTO>> getAll() {return ResponseEntity.ok(service.getAll());}

    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<ExameFisicoDTO> create(@RequestBody @Validated ExameFisicoDTO exameFisicoDTO) {
        return ResponseEntity.ok(service.create(exameFisicoDTO));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<ExameFisicoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated ExameFisicoDTO exameFisicoDTO){
        return ResponseEntity.ok(service.update(id, exameFisicoDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("Exame %s desativado com sucesso!", id));
    }


}
