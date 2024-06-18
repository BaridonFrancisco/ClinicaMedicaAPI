package com.clinicaMed.clinicaMedica.domain.paciente;

import jakarta.validation.constraints.NotNull;

public record PacienteActualizar(
        @NotNull
        Long id,
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        String calle,
        String numero,
        String complemento,
        String distrito,
        String ciudad


) {


}
