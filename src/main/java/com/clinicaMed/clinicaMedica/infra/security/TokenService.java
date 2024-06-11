package com.clinicaMed.clinicaMedica.infra.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.clinicaMed.clinicaMedica.model.Usuario;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String generarToken(Usuario usuario){
        try {
            /**/

            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("medclina")
                    .withClaim("id",usuario.getLogin())
                    .withExpiresAt(generarFechaExpiracion())
                    .withSubject(usuario.getLogin())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("No se pudo generar el token");
        }
    }
    //Subject es para quien esta asignado este token
    // verifica el token
    public String getSubject(String token) {
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("medclina")
                    .build()
                    .verify(token);
            return verifier.getSubject();
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
        }
        if(verifier!=null){
            return verifier.getSubject();
        }
        throw new RuntimeException("Verificacion del token Fallida");


    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
