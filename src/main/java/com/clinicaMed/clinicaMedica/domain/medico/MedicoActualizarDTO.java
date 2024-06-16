package com.clinicaMed.clinicaMedica.domain.medico;

import com.clinicaMed.clinicaMedica.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record MedicoActualizarDTO(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {
}
