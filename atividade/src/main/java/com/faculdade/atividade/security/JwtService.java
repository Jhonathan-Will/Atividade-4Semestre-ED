package com.faculdade.atividade.security;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final JwtEncoder enconder;
    private final JwtDecoder decoder;
    public JwtService(JwtEncoder enconder, JwtDecoder decoder) {
        this.decoder = decoder;
        this.enconder = enconder;
    }

    public Map<String, Object> decodeToken(String token) {
           Jwt jwt = decoder.decode(token); // Decodifica o token

    // Extrai os claims do token
    Map<String, Object> claims = new HashMap<>();
    claims.put("email", jwt.getClaim("email")); // Obtém o email

    return claims;
    }

    public String generateToken(Authentication authentication){
        Instant now = Instant.now();
        long expiry = 8294400L; // Tempo de expiração em segundos
    
        // Obtém o email e o role do usuário autenticado
        String email = authentication.getName(); // Geralmente o email está no "username"
        String scopes = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        // Adicione os claims necessários
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("spring-security-jwt") // Quem emitiu o token
                .issuedAt(now) // Data de emissão
                .expiresAt(now.plusSeconds(expiry)) // Data de expiração
                .subject(email) // Email como subject
                .claim("email", email)
                .claim("scope", scopes)
                .build();
    
        // Retorna o token gerado
        return enconder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
