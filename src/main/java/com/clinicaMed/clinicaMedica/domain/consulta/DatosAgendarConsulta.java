package com.clinicaMed.clinicaMedica.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        Long id,
       @NotNull Long idPaciente,
       @NotNull @Future LocalDateTime fecha


) {
}
