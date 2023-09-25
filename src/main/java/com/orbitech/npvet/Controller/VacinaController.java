package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.VacinaDTO;
import com.orbitech.npvet.Service.VacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacina")
public class VacinaController {
    @Autowired
    private VacinaService service;
    @GetMapping("/id/{id}")
    public ResponseEntity<VacinaDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<VacinaDTO>> getByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getAllByNome(nome));
    }
    @GetMapping("/animal/{animal_id}")
    public ResponseEntity<List<VacinaDTO>> getByRg(@PathVariable("animal_id") Long animal_id) {
        return ResponseEntity.ok(service.getByAnimal(animal_id));
    }
    @PostMapping("/post")
    public ResponseEntity<VacinaDTO> create(@RequestBody @Validated VacinaDTO vacinaDTO){
        return ResponseEntity.ok(service.create(vacinaDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<VacinaDTO> update(@PathVariable("id") Long id, @RequestBody @Validated VacinaDTO vacinaDTO){
        return ResponseEntity.ok(service.update(id, vacinaDTO));
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("Vacina %s deletada com sucesso!", id));
    }

}
