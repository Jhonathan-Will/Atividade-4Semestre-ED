package com.faculdade.atividade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.atividade.dto.AuthRequestDTO;
import com.faculdade.atividade.dto.AuthResponseDTO;
import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.services.AuthenticationService;
import com.faculdade.atividade.services.VendedorService;


import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthenticatedController {

    private AuthenticationService authenticationService;
    private VendedorService vendedorService;
    private PasswordEncoder passwordEncoder;
    
    public AuthenticatedController(AuthenticationService authenticationService, VendedorService vendedorService, PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.vendedorService = vendedorService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        Vendedor seller;

        try {
            seller = vendedorService.getSellerByEmail(request.email()).get();
        } catch (Exception e) {
            seller = null;
        }

        if(seller != null){
            if(seller.getPassword().equals(request.password())){
                String token = authenticationService.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
                return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(token));
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password is incorrect");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email is incorrect");
        }
    }
    

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticated( Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(authentication));
    }
}
