package com.faculdade.atividade.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.faculdade.atividade.data.DetailVendedorData;
import com.faculdade.atividade.models.Vendedor;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter{
    
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public JWTAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                            HttpServletResponse response) {
        try {
            Vendedor vendedor = new ObjectMapper().readValue(request.getInputStream(), Vendedor.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    vendedor.getEmail(),
                    vendedor.getPassword(),
                    new ArrayList<>()
                )
            );
        } catch (Exception e) {
            throw new RuntimeException("Falha ao autenticar vendedor: " + e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                           HttpServletResponse response,
                                           FilterChain chain,
                                           Authentication authResults) throws IOException, ServletException {
        
        DetailVendedorData vendedorData = (DetailVendedorData) authResults.getPrincipal();

        String token = JWT.create().withSubject(vendedorData.getEmail()).sign(Algorithm.HMAC512(jwtSecret));

        response.getWriter().write(token);
        response.getWriter().flush();;

    }
}
