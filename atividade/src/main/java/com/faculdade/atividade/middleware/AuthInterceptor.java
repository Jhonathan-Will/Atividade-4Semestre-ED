package com.faculdade.atividade.middleware;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.security.JwtService;
import com.faculdade.atividade.services.VendedorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor{

    private final VendedorService vendedorService;

    @Autowired
    private JwtService jwtService;

    AuthInterceptor(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            String authHeader = request.getHeader("Authorization");
    
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
    
            Map<String, Object> token = jwtService.decodeToken(authHeader.substring(7)); // Remove "Bearer " do token

            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            Optional<Vendedor> seller = vendedorService.getSellerByEmail((String) token.get("email"));

            if (seller.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            request.setAttribute("seller", seller.get());
            return true;
        
    }
}
