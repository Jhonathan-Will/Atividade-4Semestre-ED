package com.faculdade.atividade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.services.VendedorService;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/exercice/seller")
public class VendedorController {
    
    private VendedorService vendedorService;

    public VendedorController( VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vendedor>> getSeller(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(vendedorService.getSeller(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Vendedor>> getMethodName() {
        return ResponseEntity.status(HttpStatus.OK).body(vendedorService.getAll());
    }

}
