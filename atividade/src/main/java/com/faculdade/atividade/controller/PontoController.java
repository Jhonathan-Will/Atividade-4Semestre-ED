package com.faculdade.atividade.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.atividade.services.PontoService;

@RestController
@RequestMapping("/exercice/point")
public class PontoController {
    PontoService pontoService;

    public PontoController(PontoService pontoService) {
        this.pontoService = pontoService;
    }

    @PostMapping
    public void savePoints() {
        pontoService.savePoints();
        System.out.println("Pontos gerados com sucesso");
    }
}
