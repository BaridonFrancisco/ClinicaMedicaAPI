package com.clinicaMed.clinicaMedica.dto;

import com.clinicaMed.clinicaMedica.model.Especialidad;


public record DatosMedicoDto(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad,
        DatosDireccion direccion



) {
}
