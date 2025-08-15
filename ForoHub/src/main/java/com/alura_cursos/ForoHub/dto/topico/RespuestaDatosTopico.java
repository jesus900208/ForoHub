package com.alura_cursos.ForoHub.dto.topico;

import com.alura_cursos.ForoHub.dto.DatosUsuario;

public record RespuestaDatosTopico(
        Long id,
        DatosUsuario user,
        String curso,
        String titulo,
        String mensaje
        ) {

}
