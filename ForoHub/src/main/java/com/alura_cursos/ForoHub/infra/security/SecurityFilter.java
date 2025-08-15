package com.alura_cursos.ForoHub.infra.security;

import com.alura_cursos.ForoHub.repositorio.UsuarioRepositorio;
import com.alura_cursos.ForoHub.servicio.TokenServicio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    
    @Autowired
    private TokenServicio tokenServicio;

    @Autowired
    private UsuarioRepositorio userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException
    {
        // Lee un token
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var subject = tokenServicio.getSubject(token);

            if(subject != null)
            {
                // Token valido
                var user = userRepository.findByUsername(subject);
                System.out.println(user);
                // Forzar el inicio de sesion
                var autenticacion = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(autenticacion);
            }
        }

        filterChain.doFilter(request,response);
    }
    
}
