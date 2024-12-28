package com.example.ForoAlura.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionTopico(
        @NotBlank
        String titulo,

        @NotBlank
        String mensaje
) {
}
