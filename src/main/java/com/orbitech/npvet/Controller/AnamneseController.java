package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.AnamneseDTO;
import com.orbitech.npvet.DTO.AnamneseHistoricoDTO;
import com.orbitech.npvet.DTO.AnamnesePerguntaDTO;
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
    public ResponseEntity<AnamneseDTO> create(@RequestBody @Validated AnamneseDTO anamneseDTO) {
            return ResponseEntity.ok(anamneseService.create(anamneseDTO));
    }

    @PostMapping("adicionar/pergunta/{anamneseId}")
    public ResponseEntity<AnamnesePerguntaDTO> addQuestionAnswerToAnamnese(
            @PathVariable Long anamneseId,
            @RequestBody AnamnesePerguntaDTO request
    ) {
            return ResponseEntity.ok(anamneseService.addQuestionAnswerToAnamnese(anamneseId,request));
    }


    @PostMapping("/atualizar/progresso-medico/{id}")
    public ResponseEntity<?> updateProgressoMedico(
            @PathVariable Long id,
            @RequestBody AnamneseHistoricoDTO progressoMedico
    ) {
            return ResponseEntity.ok(anamneseService.updateProgressoMedico(id, progressoMedico));
        }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                         @RequestBody @Validated AnamneseDTO anamneseDTO) {
            return ResponseEntity.ok(anamneseService.update(id, anamneseDTO));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.anamneseService.delete(id);
        return ResponseEntity.ok().body("Anamnese excluída com sucesso!");
    }


}
