package com.clinicaMed.clinicaMedica.domain.medico;


import com.clinicaMed.clinicaMedica.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosMedicos(
        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        String documento,
        @NotBlank
        String telefono,
        @NotNull
        Especialidad especialidad,
        @Valid
        DatosDireccion direccion
) {
}
