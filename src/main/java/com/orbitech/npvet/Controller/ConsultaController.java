package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.ConsultaDTO;
import com.orbitech.npvet.Service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
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
    public ResponseEntity<ConsultaDTO>create(@RequestBody ConsultaDTO consultaDTO){
        return ResponseEntity.ok(service.create(consultaDTO));
    }
    @PutMapping("/update{id}")
    public ResponseEntity<ConsultaDTO>update(@PathVariable("id") final long id, ConsultaDTO consultaDTO){
        return ResponseEntity.ok(service.update(id, consultaDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable("id") final long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("Consulta com id [%s] deletada com sucesso.",id));
    }
}
