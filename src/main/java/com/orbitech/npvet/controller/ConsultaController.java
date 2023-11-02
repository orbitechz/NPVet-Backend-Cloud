package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.ConsultaDTO;
import com.orbitech.npvet.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO>getById(@PathVariable("id")final long id) throws Exception {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<ConsultaDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping("/post")
    public ResponseEntity<ConsultaDTO>create(@RequestBody @Validated ConsultaDTO consultaDTO){
        return ResponseEntity.ok(service.create(consultaDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ConsultaDTO>update(@PathVariable("id") final long id,@RequestBody @Validated ConsultaDTO consultaDTO){
        return ResponseEntity.ok(service.update(id, consultaDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable("id") final long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("Consulta com id [%s] deletada com sucesso.",id));
    }

    @GetMapping("/animal/nome/{nome}")
    public ResponseEntity<List<ConsultaDTO>>getAnimalByName(@PathVariable("nome")String nome){
        return ResponseEntity.ok(service.getAnimalByName(nome));
    }

    @GetMapping("/animal/id/{id}")
    public ResponseEntity<List<ConsultaDTO>>getAnimalById(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.getAnimalById(id));
    }

    @GetMapping("/veterinario/nome/{nome}")
    public ResponseEntity<List<ConsultaDTO>>getVeterinarioByNome(@PathVariable("nome")String nome){
        return ResponseEntity.ok(service.getVeterinarioByName(nome));

    }

    @GetMapping("/veterinario/{id}")
    public ResponseEntity<List<ConsultaDTO>>getVeterinarioById(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.getVeterinarioById(id));
    }

    @GetMapping("/anamnese/{id}")
    public ResponseEntity<List<ConsultaDTO>>getAnamneseById(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.getAnamneseId(id));
    }
    @GetMapping("/em-andamento")
    public ResponseEntity<List<ConsultaDTO>>getConsultasEmAndamento(){
        List<ConsultaDTO>response = service.getConsultasEmAndamento();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/concluida")
    public ResponseEntity<List<ConsultaDTO>>getConsultasConcluida(){
        List<ConsultaDTO>response = service.getConsultasConcluida();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cancelada")
    public ResponseEntity<List<ConsultaDTO>>getConsultasCancelada(){
        List<ConsultaDTO>response = service.getConsultasCancelada();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/agendada")
    public ResponseEntity<List<ConsultaDTO>>getConsultasAgendadas(){
        List<ConsultaDTO>response = service.getConsultaAgendada();
        return ResponseEntity.ok(response);
    }
}
