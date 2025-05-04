package com.faculdade.atividade.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.atividade.models.Ponto;
import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.services.PontoService;
import com.faculdade.atividade.dto.GetPointDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/exercice/point")
public class PontoController {
    private PontoService pontoService;

    public PontoController(PontoService pontoService) {
        this.pontoService = pontoService;
    }

    @PostMapping
    public ResponseEntity<List<Ponto>> savePoints() {
       return ResponseEntity.status(HttpStatus.OK).body(pontoService.savePoints());
    }

    @PutMapping("/convert")
    public ResponseEntity<?> getAllPoints(@RequestBody @Valid GetPointDTO valueStr, HttpServletRequest request) {
        try {
            int value = Integer.parseInt(valueStr.value().trim());
            if (value <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            Vendedor seller = (Vendedor) request.getAttribute("seller");
            if (seller != null) {
                return ResponseEntity.status(HttpStatus.OK).body(pontoService.convert(seller.getId(), value));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
