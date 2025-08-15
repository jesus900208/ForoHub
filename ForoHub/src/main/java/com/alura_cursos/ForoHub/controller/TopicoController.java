package com.alura_cursos.ForoHub.controller;

import com.alura_cursos.ForoHub.dto.ActualizarDatosDeTopico;
import com.alura_cursos.ForoHub.dto.DatosUsuario;
import com.alura_cursos.ForoHub.dto.topico.DatosTopico;
import com.alura_cursos.ForoHub.dto.topico.ListaDeDatosDeTopico;
import com.alura_cursos.ForoHub.dto.topico.RespuestaDatosTopico;
import com.alura_cursos.ForoHub.modelo.Topico;
import com.alura_cursos.ForoHub.repositorio.TopicoRepositorio;
import com.alura_cursos.ForoHub.servicio.TopicoServicio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    
    @Autowired
    private TopicoServicio servicio;
    
    @Autowired
    TopicoRepositorio repositorio;
    
    // Publica un nuevo topico
    @PostMapping
    @Transactional
    public ResponseEntity setTopic(
            @RequestBody
            @Valid
            DatosTopico topic
    )
    {
        var response = servicio.guardar(topic);

        Object responseBody = new Object() {
            public final int httpStatus = HttpStatus.OK.value();
            public final Object topic = response;
        };

        return ResponseEntity.ok(responseBody);
    }

    // Trae todos los topicos
    @GetMapping
    public Page<ListaDeDatosDeTopico> getTopics(
            @PageableDefault(size = 5, sort = "fechaCreacion", direction = Sort.Direction.DESC)
            Pageable pageable
    )
    {
        return repositorio.findByStatusTrue(pageable)
                .map(ListaDeDatosDeTopico::new);
    }

    // Trae un topico por ID
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> getATopic(
            @PathVariable
            Long id
    )
    {
        Topico topic = repositorio.getReferenceById(id);
        var topicData = new DatosTopico(
          topic.getId(),
          topic.getUsuario().getId(),
          topic.getCurso(),
          topic.getTitulo(),
          topic.getMensaje(),
          topic.getFechaCreacion()
        );

        Object responseBody = new Object() {
            public final int httpStatus = HttpStatus.OK.value();
            public final Object topic = topicData;
        };

        return ResponseEntity.ok(responseBody);
    }

    // Actualizar un topico
    @PutMapping()
    @Transactional
    public ResponseEntity<Object> updateTopic(
            @RequestBody
            @Valid
            ActualizarDatosDeTopico topicData
    )
    {
        Topico topic = repositorio.getReferenceById(topicData.id());
        topic.actualizarDatosTopico(topicData);

        var response = new RespuestaDatosTopico(
          topic.getId(),
          new DatosUsuario(
                  topic.getUsuario().getId(),
                  topic.getUsuario().getUsername()
          ),
          topic.getCurso(),
          topic.getTitulo(),
          topic.getMensaje()
        );

        Object responseBody = new Object() {
            public final int httpStatus = HttpStatus.OK.value();
            public final Object topic = response;
        };

        return ResponseEntity.ok(responseBody);
    }

    // Eliminar topico de la DB
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaDatosTopico> deleteTopic(
            @PathVariable
            Long
            id
    ){
        Topico topic = repositorio.getReferenceById(id);
        topic.disableTopico();
        return ResponseEntity.noContent().build();
    }
    
}
