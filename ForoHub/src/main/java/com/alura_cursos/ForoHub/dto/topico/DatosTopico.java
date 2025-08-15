package com.alura_cursos.ForoHub.dto.topico;

import com.alura_cursos.ForoHub.modelo.Topico;
import java.time.LocalDateTime;

public record DatosTopico(
        Long id,
        Long usuario,
        String curso,
        String titulo,
        String mensaje,
        LocalDateTime fecha
        ) {
    public DatosTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getUsuario().getId(),
                topico.getCurso(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion()
        );
    }
}
