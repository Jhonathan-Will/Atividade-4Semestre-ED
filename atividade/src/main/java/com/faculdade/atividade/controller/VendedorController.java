package com.faculdade.atividade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.atividade.dto.VendedorDto;
import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.services.VendedorService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


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
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vendedor>> getSeller(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(vendedorService.getSeller(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Vendedor>> getMethodName(@RequestParam String param) {
        return ResponseEntity.status(HttpStatus.FOUND).body(vendedorService.getAll());
    }
    
}
