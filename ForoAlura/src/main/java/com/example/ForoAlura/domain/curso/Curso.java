package com.example.ForoAlura.domain.curso;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;


    public Curso() {
    }

    public Curso(DatosRegistroCurso datos) {
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
