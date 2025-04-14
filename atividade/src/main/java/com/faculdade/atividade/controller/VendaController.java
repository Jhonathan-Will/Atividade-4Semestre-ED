package com.faculdade.atividade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.atividade.dto.VendaDTO;
import com.faculdade.atividade.models.Venda;
import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.services.VendaService;
import com.faculdade.atividade.services.VendedorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/exercice/sale")
public class VendaController {

    private VendaService vendaService;

    public VendaController(VendaService vendaService, VendedorService vendedorService) {
        this.vendaService = vendaService;
    }
    
    @PostMapping
    public ResponseEntity<?> saveSell(@RequestBody @Valid VendaDTO sell, HttpServletRequest request) {
        Vendedor seller = (Vendedor) request.getAttribute("seller");

        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.save(sell, seller));
    }

    @GetMapping
    public ResponseEntity<?> getAllSells() {
        return ResponseEntity.status(HttpStatus.OK).body(vendaService.getAllSells());
    }
    
    @GetMapping("/seller")
    public ResponseEntity<List<Venda>> getAllSellsBySellerId(HttpServletRequest request) {
        Vendedor seller = (Vendedor) request.getAttribute("seller");

        return ResponseEntity.status(HttpStatus.OK).body(vendaService.getSellBySellerId(seller.getId()));
    }

}
