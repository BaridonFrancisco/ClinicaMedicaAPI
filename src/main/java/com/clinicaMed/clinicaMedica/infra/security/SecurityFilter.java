package com.clinicaMed.clinicaMedica.infra.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filtro implementado");
        var token=request.getHeader("Authorization");
        if(token.isBlank() || token.isEmpty()){
            throw new RuntimeException("token esta vacio");
        }
        token=token.replace("bearer","");
        System.out.println(token);
        filterChain.doFilter(request,response);
    }
}
