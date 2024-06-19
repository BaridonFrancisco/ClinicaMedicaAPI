package com.clinicaMed.clinicaMedica.domain.paciente;

import com.clinicaMed.clinicaMedica.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record PacienteActualizar(
        @NotNull
        Long id,
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        DatosDireccion datosDireccion


) {


}
