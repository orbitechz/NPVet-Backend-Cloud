package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.Entity.Anamnese;
import com.orbitech.npvet.Service.AnamneseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anamnese")
public class AnamneseController {

    @Autowired
    private AnamneseService anamneseService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        AnamneseDTO anamneseDTO = anamneseService.getById(id);
        return anamneseDTO == null ?
                ResponseEntity.badRequest().body("O ID solicitado não foi encontrado no banco de dados.")
                : ResponseEntity.ok(anamneseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<AnamneseDTO> anamneseDTO = anamneseService.getAll();
        return anamneseDTO.isEmpty() ?
                ResponseEntity.badRequest().body("Nenhum registro foi encontrado no banco de dados.")
                : ResponseEntity.ok(anamneseDTO);
    }

    @GetMapping("/tutor/{cpf}")
    public ResponseEntity<?> getByTutorCpf(@PathVariable("cpf") String cpf) {
        List<AnamneseDTO> anamneseDTO = anamneseService.getByTutorCpf(cpf);
        return anamneseDTO.isEmpty() ?
                ResponseEntity.badRequest().body("Nenhum registro foi encontrado no banco de dados.")
                : ResponseEntity.ok(anamneseDTO);
    }

    @GetMapping("/tutor/{cpf}/animal/{nome}")
    public ResponseEntity<?> getByTutorCpfAndAnimal(@PathVariable("cpf") String cpf,
                                                    @PathVariable("nome") String nome) {
        List<AnamneseDTO> anamneseDTO = anamneseService.getByTutorCpfAndAnimal(cpf,nome);
        return anamneseDTO.isEmpty() ?
                ResponseEntity.badRequest().body("Nenhum registro foi encontrado no banco de dados.")
                : ResponseEntity.ok(anamneseDTO);
    }

    @PostMapping("/post")
    public ResponseEntity<String> create(@RequestBody @Validated AnamneseDTO anamneseDTO) {
        try {
            Anamnese anamnese = anamneseService.create(anamneseDTO);
            return ResponseEntity.ok("O registro da anamnese foi realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Ocorreu um erro durante o cadastro: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @RequestBody @Validated AnamneseDTO anamneseDTO) {
        try {
            Anamnese anamnese = anamneseService.update(id, anamneseDTO);
            return ResponseEntity.ok("O registro da anamnese foi atualizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Ocorreu um erro durante a atualização: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.anamneseService.delete(id);
        return ResponseEntity.ok().body("Anamnese excluída com sucesso!");
    }


}
