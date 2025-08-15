package com.alura_cursos.ForoHub.repositorio;

import com.alura_cursos.ForoHub.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
    
    Page<Topico> findByStatusTrue(Pageable pageable);
    
}
