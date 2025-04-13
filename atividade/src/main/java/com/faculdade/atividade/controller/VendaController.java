package com.faculdade.atividade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.atividade.dto.VendaDTO;
import com.faculdade.atividade.services.VendaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/exercice/sale")
public class VendaController {

    private VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }



    @PostMapping
    public ResponseEntity<?> saveSell(@RequestBody @Valid VendaDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.save(request));
    }
    
}
