package com.clinicaMed.clinicaMedica.infra.security;

import com.clinicaMed.clinicaMedica.repository.UsuarioRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filtro iniciando");
        var token=request.getHeader("Authorization");
       /* if(token==null  || token.isEmpty()){
            throw new RuntimeException("token esta vacio");
        }*/
        if(token!=null){
            System.out.println("El token no esta nulo");
            token=token.replace("Bearer","");
            System.out.println(token);
            var suject=tokenService.getSubject(token);
            if(suject!=null){
                UserDetails usuario=usuarioRepository.findByLogin(suject);
                Authentication authentication=new UsernamePasswordAuthenticationToken(usuario,
                        null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request,response);
    }
}
