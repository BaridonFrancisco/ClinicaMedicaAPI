package com.clinicaMed.clinicaMedica.domain.consulta;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(
        Long id,
        Long idPaciente,
        Long idMedico,
        LocalDateTime fecha
) {

    DatosDetalleConsulta(Consulta consulta) {
        this(consulta.getId(),consulta.getPaciente().getId(),consulta.getMedico().getId(),consulta.getData());
    }
}