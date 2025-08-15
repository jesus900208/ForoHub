package com.alura_cursos.ForoHub.servicio;

import com.alura_cursos.ForoHub.modelo.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class TokenServicio {

    public String generateToken(Usuario user) {
        Algorithm algorithm = Algorithm.HMAC256("root");

        return JWT.create()
                .withIssuer("Foro Hub")
                .withSubject(user.getUsername().toLowerCase(Locale.ROOT))
                .withClaim("id",user.getId())
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);
    }

    // genera un tiempo de expiracion apartir de la fechaCreacion actual
    private Instant generateExpirationDate()
    {
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-06:00"));
    }

    // Obtener el usuario
    public String getSubject(String token)
    {
        if (token == null)
        {
            throw new RuntimeException();
        }

        DecodedJWT verifier = null;

        // Validar la firma del token
        try {

            Algorithm algorithm = Algorithm.HMAC256("root");

            verifier = JWT.require(algorithm)
                    .withIssuer("foro hub")
                    .build()
                    .verify(token);

            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token no valido");
        }

        if (verifier.getSubject() == null)
        {
            throw new RuntimeException("No se pudo validar el token");
        }

        return verifier.getSubject();
    }
    
}
