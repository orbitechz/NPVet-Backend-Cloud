package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.DTO.AnamnesePerguntaDTO;
import com.orbitech.npvet.Entity.Anamnese;
import com.orbitech.npvet.Entity.AnamnesePergunta;
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
    public ResponseEntity<AnamneseDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(anamneseService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnamneseDTO>> getAll() {
        return ResponseEntity.ok(anamneseService.getAll());
    }

    @GetMapping("/tutor/{cpf}")
    public ResponseEntity<List<AnamneseDTO>> getByTutorCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(anamneseService.getByTutorCpf(cpf));
    }

    @GetMapping("/tutor/{cpf}/animal/{nome}")
    public ResponseEntity<List<AnamneseDTO>> getByTutorCpfAndAnimal(@PathVariable("cpf") String cpf,
                                                    @PathVariable("nome") String nome) {
        return ResponseEntity.ok(anamneseService.getByTutorCpfAndAnimal(cpf,nome));
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

    @PostMapping("adicionar/pergunta/{anamneseId}")
    public Anamnese addQuestionAnswerToAnamnese(
            @PathVariable Long anamneseId,
            @RequestBody AnamnesePergunta request
    ) {
        return anamneseService.addQuestionAnswerToAnamnese(anamneseId, request);
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
