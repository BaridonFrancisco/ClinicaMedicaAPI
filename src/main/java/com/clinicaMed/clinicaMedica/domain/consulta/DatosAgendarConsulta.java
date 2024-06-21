package com.clinicaMed.clinicaMedica.domain.consulta;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        Long id,
        @NotNull Long idPaciente,
        @NotNull Long idMedico,
       
        @NotNull @Future LocalDateTime fecha


) {
}
