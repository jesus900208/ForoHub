package com.alura_cursos.ForoHub.dto.topico;

import com.alura_cursos.ForoHub.modelo.Topico;
import java.time.LocalDateTime;

public record ListaDeDatosDeTopico(
        Long id,
        String curso,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status
        ) {
    public ListaDeDatosDeTopico(Topico topic)
    {
        this(
                topic.getId(),
                topic.getCurso(),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion(),
                topic.getStatus()
        );
    }
}
