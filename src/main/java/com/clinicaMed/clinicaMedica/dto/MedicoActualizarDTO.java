package com.clinicaMed.clinicaMedica.dto;

import jakarta.validation.constraints.NotNull;

public record MedicoActualizarDTO(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {
}
