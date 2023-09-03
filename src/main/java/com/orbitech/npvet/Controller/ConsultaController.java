package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.ConsultaDTO;
import com.orbitech.npvet.Service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
