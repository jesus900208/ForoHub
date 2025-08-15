package com.alura_cursos.ForoHub.repositorio;

import com.alura_cursos.ForoHub.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    UserDetails findByUsername(String username);
}
