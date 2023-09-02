package com.orbitech.npvet.Controller;

import com.orbitech.npvet.Service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @Autowired
    public PerguntaService perguntaService;

}
