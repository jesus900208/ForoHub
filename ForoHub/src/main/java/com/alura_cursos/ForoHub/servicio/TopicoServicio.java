package com.alura_cursos.ForoHub.servicio;

import com.alura_cursos.ForoHub.dto.topico.DatosTopico;
import com.alura_cursos.ForoHub.infra.exception.IntegrityValidation;
import com.alura_cursos.ForoHub.modelo.Topico;
import com.alura_cursos.ForoHub.repositorio.TopicoRepositorio;
import com.alura_cursos.ForoHub.repositorio.UsuarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TopicoServicio {
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    TopicoRepositorio topicoRepositorio;
    
    //Guardar un topico
    public DatosTopico guardar(
            @RequestBody
            @Valid
            DatosTopico datosTopico
    ) {
        if (!usuarioRepositorio.findById(datosTopico.usuario()).isPresent()) {
            throw new IntegrityValidation("Este usuario no existe");
        }
        
        var user = usuarioRepositorio.findById(datosTopico.usuario()).get();
        Topico topico;
        topico = new Topico(
                null,
                user,
                datosTopico.curso(),
                datosTopico.titulo(),
                datosTopico.mensaje(),
                datosTopico.fecha(),
                true
        );
        
        topicoRepositorio.save(topico);
        return new DatosTopico(topico);
    } 

}
