package com.clinicaMed.clinicaMedica.domain.paciente;

import com.clinicaMed.clinicaMedica.domain.direccion.Direccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosPaciente(
        @NotBlank String nombre,
        String email,
        @NotBlank String telefono,
        @NotBlank String documento,
        @Valid Direccion direccion
) {

}
