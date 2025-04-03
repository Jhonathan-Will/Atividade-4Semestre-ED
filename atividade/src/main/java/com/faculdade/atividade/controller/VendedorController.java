package com.faculdade.atividade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.atividade.dto.VendedorDto;
import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.services.VendedorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/exercice/seller")
public class VendedorController {
    
    private VendedorService vendedorService;

    public VendedorController( VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    public ResponseEntity<Vendedor> saveSeller(@RequestBody VendedorDto vendedorDto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorService.saveSeller(vendedorDto));
    }
    
}
