package com.orbitech.npvet.Controller;

import com.orbitech.npvet.DTO.TutorDTO;
import com.orbitech.npvet.Service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    @Autowired
    private TutorService service;

    @GetMapping("/{id}")
    public ResponseEntity<TutorDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/post")
    public ResponseEntity<TutorDTO> create(@RequestBody @Validated TutorDTO tutorDTO){
        return ResponseEntity.ok(service.create(tutorDTO));
    }
}
