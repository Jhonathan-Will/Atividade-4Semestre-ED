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
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/exercice/sale")
public class VendaController {

    private VendaService vendaService;
    private VendedorService vendedorService;

    public VendaController(VendaService vendaService, VendedorService vendedorService) {
        this.vendaService = vendaService;
        this.vendedorService = vendedorService;
    }

    @PostMapping
    public ResponseEntity<?> saveSell(@RequestBody @Valid VendaDTO request, Authentication authentication) {
        String authenticatedUserEmail = authentication.getName();
        Optional<Vendedor> seller = vendedorService.getSellerByEmail(authenticatedUserEmail);

        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.save(request, seller.get()));
    }

    @GetMapping
    public ResponseEntity<List<Venda>> getAllSells() {
        return ResponseEntity.status(HttpStatus.OK).body(vendaService.getAllSells());
    }
    
    @GetMapping("/seller/{id}")
    public ResponseEntity<List<Venda>> getAllSellsBySellerId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(vendaService.getSellBySellerId(id));
    }

    @GetMapping("/me")
    public ResponseEntity<?> teste(HttpServletRequest request, VendaDTO venda){

        return ResponseEntity.ok("Seller: "+ request.getAttribute("seller"));
    } 

}
