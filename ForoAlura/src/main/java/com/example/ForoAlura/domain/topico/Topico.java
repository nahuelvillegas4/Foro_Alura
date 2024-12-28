package com.example.ForoAlura.domain.topico;

import com.example.ForoAlura.domain.curso.Curso;
import com.example.ForoAlura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private Boolean status;


    public Topico() {}


    public Topico(DatosRegistroTopico datos, Curso curso, Usuario usuario) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fecha = LocalDateTime.now();
        this.autor = usuario;
        this.curso = curso;
        this.status = true;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public Boolean getStatus() {
        return status;
    }

    public void desactivarTopico() {
        this.status = false;
    }

    public void actualizar(DatosActualizacionTopico datos) {
        if(datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if(datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }
}




