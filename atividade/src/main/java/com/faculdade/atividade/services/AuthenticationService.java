package com.faculdade.atividade.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.faculdade.atividade.security.JwtService;

@Service
public class AuthenticationService {

    private JwtService jwtService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

}
