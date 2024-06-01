package com.clinicaMed.clinicaMedica.dto;


import com.clinicaMed.clinicaMedica.model.Especialidad;
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
        //@NotBlank
        //String telefono,
        @NotNull
        Especialidad especialidad,
        @Valid
        DatosDireccion direccion
) {
}
