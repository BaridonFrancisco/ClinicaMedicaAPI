package com.clinicaMed.clinicaMedica.controller;
import com.clinicaMed.clinicaMedica.dto.DatosAutentificacionUsuario;
import com.clinicaMed.clinicaMedica.dto.JWTtokenDTO;
import com.clinicaMed.clinicaMedica.infra.security.TokenService;
import com.clinicaMed.clinicaMedica.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutentificacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity autentificarUsuario(@RequestBody @Valid DatosAutentificacionUsuario datosAutentificacionUsuario){
        Authentication authToken=new UsernamePasswordAuthenticationToken(datosAutentificacionUsuario.login()
                ,datosAutentificacionUsuario.clave());
        Authentication authentication= authenticationManager.authenticate(authToken);
        String JTWtoken=tokenService.generarToken((Usuario)authentication.getPrincipal());
        return ResponseEntity.ok(new JWTtokenDTO(JTWtoken));
    }

}
