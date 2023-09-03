package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.AnimalDTO;
import com.orbitech.npvet.DTO.ExameFisicoDTO;
import com.orbitech.npvet.Service.ExameFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examefisico")
public class ExameFisicoController {

    @Autowired
    private ExameFisicoService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<ExameFisicoDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/animal/nome/{nome}")
    public ResponseEntity<List<ExameFisicoDTO>> getByAnimalNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getByNomeAnimal(nome));
    }

    @GetMapping("/animal/id/{id}")
    public ResponseEntity<List<ExameFisicoDTO>> getByAnimalId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getByIdAnimal(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExameFisicoDTO>> getAll() {return ResponseEntity.ok(service.getAll());}

    @PostMapping("/post")
    public ResponseEntity<ExameFisicoDTO> create(@RequestBody @Validated ExameFisicoDTO exameFisicoDTO) {
        return ResponseEntity.ok(service.create(exameFisicoDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExameFisicoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated ExameFisicoDTO exameFisicoDTO){
        return ResponseEntity.ok(service.update(id, exameFisicoDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("Exame %s desativado com sucesso!", id));
    }


}
