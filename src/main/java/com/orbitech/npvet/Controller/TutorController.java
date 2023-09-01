package com.orbitech.npvet.Controller;

import com.orbitech.npvet.Service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    @Autowired
    private TutorService service;


}
