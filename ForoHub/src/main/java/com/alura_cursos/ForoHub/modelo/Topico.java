package com.alura_cursos.ForoHub.modelo;

import com.alura_cursos.ForoHub.dto.ActualizarDatosDeTopico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "topicos")
@Entity(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid_usuario")
    private Usuario usuario;
    
    @Column(name = "nombre_curso")
    private String curso;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status = Boolean.TRUE;
    
    public void actualizarDatosTopico(ActualizarDatosDeTopico topico) {
        if (topico.titulo() != null) {
            this.titulo = topico.titulo();
        }
        if (topico.mensaje() != null) {
            this.mensaje = topico.mensaje();
        }
    }
    
    public void disableTopico() {
        this.status = false;
    }
}
