package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    @Autowired
    private TutorService service;
    @GetMapping("/id/{id}")
    public ResponseEntity<TutorDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<TutorDTO>> getByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getAllByNome(nome));
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<TutorDTO> getByCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(service.getByCpf(cpf));
    }
    @GetMapping("/rg/{rg}")
    public ResponseEntity<TutorDTO> getByRg(@PathVariable("rg") String rg) {
        return ResponseEntity.ok(service.getByRg(rg));
    }
    @PostMapping("/post")
    public ResponseEntity<TutorDTO> create(@RequestBody @Validated TutorDTO tutorDTO){
        return ResponseEntity.ok(service.create(tutorDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<TutorDTO> update(@PathVariable("id") Long id, @RequestBody @Validated TutorDTO tutorDTO){
        return ResponseEntity.ok(service.update(id, tutorDTO));
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("Tutor %s desativado com sucesso!", id));
    }
}
