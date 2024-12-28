package com.example.ForoAlura.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String estado,
        LocalDateTime fechaDeCreacion,
        String nombreAutor,
        String nombreCurso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus() ? "Activo" : "Finalizado",
                topico.getFecha(),
                topico.getAutor().getLogin(),
                topico.getCurso().getNombre()
        );
    }
}
